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
        var result = ""

        // 45분 전이면
        if (m.toInt() < 45) {
            // 0시면 2330
            if (h == "00") result = "2330"
            // 아니면 1시간 전 날씨 정보 부르기
            else {
                var resultH = h.toInt() - 1
                // 1자리면 0 붙여서 2자리로 만들기
                if (resultH < 10) result = "0" + resultH + "30"
                // 2자리면 그대로
                else result = resultH.toString() + "30"
            }
        }
        // 45분 이후면 바로 정보 받아오기
        else result = h + "30"

        return result
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postservice = RetrofitClient.getRetrofit().create(WeatherService::class.java)

        // 준비 단계 : base_date(발표 일자), base_time(발표 시각)
        // 현재 날짜, 시간 정보 가져오기
        val cal = Calendar.getInstance()
        base_date = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(cal.time) // 현재 날짜
        val timeH = SimpleDateFormat("HH", Locale.getDefault()).format(cal.time) // 현재 시각
        val timeM = SimpleDateFormat("HH", Locale.getDefault()).format(cal.time) // 현재 분
        // API 가져오기 적당하게 변환
        base_time = getBaseTime(timeH, timeM)
        // 현재 시각이 00시이고 45분 이하여서 baseTime이 2330이면 어제 정보 받아오기
        if (timeH == "00" && base_time == "2330") {
            cal.add(Calendar.DATE, -1).toString()
            base_date = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(cal.time)
        }


        postservice.GetWeather(60, 1, "JSON", base_date, base_time, nx, ny)
            .enqueue(object : Callback<WEATHER> {
                override fun onResponse(call: Call<WEATHER>, response: Response<WEATHER>) {
                    if (response.isSuccessful) {
                        val it: List<ITEM> = response.body()!!.response.body.items.item

                        val weatherArr = arrayOf(ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather(), ModelWeather())

                        // 배열 채우기
                        val totalCount = response.body()!!.response.body.totalCount - 1
//                        for (i in 0..totalCount) {
//                            when(it[i].category) {
//                                "REH" -> weatherArr[index].humidity = it[i].fcstValue     // 습도
//                                "SKY" -> {
//                                    if (it[i].fcstValue == "1")
//                                        weatherArr[index].sky = "맑음"
//                                    else if (it[i].fcstValue == "3")
//                                        weatherArr[index].sky = "구름"
//                                    else if (it[i].fcstValue == "4")
//                                        weatherArr[index].sky = "구름"
//
//                                    weatherArr[index].sky = it[i].fcstValue
//                                }          // 하늘 상태
//                                "PTY" -> {
//                                    if (it[i].fcstValue == "0")
//                                        weatherArr[index].skyform = "없음"
//                                    else if (it[i].fcstValue == "1")
//                                        weatherArr[index].skyform = "비"
//                                    else if(it[i].fcstValue == "2" || it[i].fcstValue == "3")
//                                        weatherArr[index].skyform = "눈"
//                                    else if (it[i].fcstValue == "4")
//                                        weatherArr[index].skyform = "비"
//                                }          // 강수형태 상태
//                                "T1H" -> weatherArr[index].temp = it[i].fcstValue         // 기온
//                                "WSD" -> weatherArr[index].windspeed = it[i].fcstValue         // 풍속
//                                "POP" -> weatherArr[index].rainPer = it[i].fcstValue         // 강수 확률
//                                else -> continue
//                            }
//                        }

                        // 각 날짜 배열 시간 설정
                        for (i in 0..5) weatherArr[i].fcstTime = it[i].fcstTime

                        Log.d("test", "${weatherArr[0].fcstTime}")

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