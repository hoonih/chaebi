package com.example.chaebi.ui.main.weather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat
import com.example.chaebi.R
import com.example.chaebi.data.model.ITEM
import com.example.chaebi.data.model.ModelWeather
import com.example.chaebi.data.model.WEATHER
import com.example.chaebi.databinding.FragmentNameBinding
import com.example.chaebi.databinding.FragmentWeatherBinding
import com.example.chaebi.remote.RetrofitClient
import com.example.chaebi.remote.service.WeatherService
import com.example.chaebi.ui.main.weather.adapter.WeatherAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding: FragmentWeatherBinding
        get() = requireNotNull(_binding) {"바인딩 객체를 생성해주세요."}



    private var base_date = "20210510"  // 발표 일자
    private var base_time = "1400"      // 발표 시각
    private var nx = "55"               // 예보지점 X 좌표
    private var ny = "127"              // 예보지점 Y 좌표

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getBaseTime(h : String, m : String) : String {
        Log.d("test", "$h $m")
        var setresult : Int
        var clockresult = ""

        setresult = h.toInt()
        if (m.toInt() < 10) {
            setresult = h.toInt() - 1
        }

        setresult -= (setresult + 1) % 3

        if (setresult / 10 == 0)
            clockresult = "0" + setresult.toString() + "00"
        else
            clockresult = setresult.toString() + "00"

        Log.d("test", "$clockresult")
        return clockresult
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postservice = RetrofitClient.getRetrofit().create(WeatherService::class.java)

        // 준비 단계 : base_date(발표 일자), base_time(발표 시각)
        // 현재 날짜, 시간 정보 가져오기
        val cal = Calendar.getInstance()
        base_date = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(cal.time) // 현재 날짜
        val timeH = SimpleDateFormat("HH", Locale.getDefault()).format(cal.time) // 현재 시각
        val timeM = SimpleDateFormat("mm", Locale.getDefault()).format(cal.time) // 현재 분
        // API 가져오기 적당하게 변환
        base_time = getBaseTime(timeH, timeM)
        // 현재 시각이 00시이고 45분 이하여서 baseTime이 2330이면 어제 정보 받아오기
        if (timeH == "00" && base_time == "0-100") {
            cal.add(Calendar.DATE, -1).toString()
            base_date = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(cal.time)
            base_time = "2300"
        }

        postservice.GetWeather(140, 1, "JSON", base_date, base_time, nx, ny)
            .enqueue(object : Callback<WEATHER> {
                override fun onResponse(call: Call<WEATHER>, response: Response<WEATHER>) {
                    if (response.isSuccessful) {
                        val it: List<ITEM> = response.body()!!.response.body.items.item

                        val weatherArr = arrayOf(ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather(),ModelWeather(),ModelWeather())

                        // 배열 채우기
                        val totalCount = response.body()!!.response.body.totalCount - 1
                        val timecheck: Array<Int> = Array(25) { 0 }
                        var index = -1
                        for (i in 0..140) {
                            val fcsttime : Int = "${it[i].fcstTime[0]}${it[i].fcstTime[1]}".toInt()
                            if (timecheck[fcsttime] == 0) {

                                timecheck[fcsttime] = 1
                                index ++
                                if (index >= 10)
                                {
                                    break
                                }
                                weatherArr[index].fcstTime = it[i].fcstTime

                                Log.d("test", "${it[i].fcstTime}")
                            }
                            when(it[i].category) {
                                "REH" -> weatherArr[index].humidity = it[i].fcstValue     // 습도
                                "SKY" -> {
                                    if (it[i].fcstValue == "1")
                                        weatherArr[index].sky = "맑음"
                                    else if (it[i].fcstValue == "3")
                                        weatherArr[index].sky = "구름"
                                    else if (it[i].fcstValue == "4")
                                        weatherArr[index].sky = "구름"
                                }          // 하늘 상태
                                "PTY" -> {
                                    if (it[i].fcstValue == "0")
                                        weatherArr[index].skyform = "없음"
                                    else if (it[i].fcstValue == "1")
                                        weatherArr[index].skyform = "비"
                                    else if(it[i].fcstValue == "2" || it[i].fcstValue == "3")
                                        weatherArr[index].skyform = "눈"
                                    else if (it[i].fcstValue == "4")
                                        weatherArr[index].skyform = "비"
                                }          // 강수형태 상태
                                "TMP" -> weatherArr[index].temp = it[i].fcstValue         // 기온
                                "WSD" -> weatherArr[index].windspeed = it[i].fcstValue         // 풍속
                                "POP" -> weatherArr[index].rainPer = it[i].fcstValue         // 강수 확률
                                else -> continue
                            }
                        }


                        val weatherAdapter = WeatherAdapter(requireContext())
                        binding.rvWeather.adapter= weatherAdapter
                        weatherAdapter.setFriendList(weatherArr)

                    } else {
                        try {
                            val body = response.errorBody()!!.string()
                            Log.d("chaebi", "error - body : $body")
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<WEATHER>, t: Throwable) {
                    Log.d("theia", "API FAIL: ${call}")
                }

            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}