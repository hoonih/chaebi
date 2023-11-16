package com.example.chaebi.ui.main.weather.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.chaebi.R
import com.example.chaebi.data.model.ModelWeather
import com.example.chaebi.databinding.ItemSunBinding

class WeatherViewHolder (private val binding: ItemSunBinding) :
    RecyclerView.ViewHolder(binding.root) {
        var skyname = ""
        fun onBind(weather: ModelWeather) {
            if (weather.skyform == "없음")
            {
                if (weather.sky == "맑음")
                {
                    binding.icon.setImageResource(R.drawable.ic_sunny)
                    skyname = "맑음"
                }
                else if (weather.sky == "구름")
                {

                    binding.icon.setImageResource(R.drawable.ic_cloudy)
                    skyname = "구름"
                }
            }
            else if (weather.skyform == "비")
            {

                binding.icon.setImageResource(R.drawable.ic_raniy)
                skyname = "비"
            }

            else if (weather.skyform == "눈")
            {
                binding.icon.setImageResource(R.drawable.ic_snow)
                skyname = "눈"

            }
            binding.txTmp.text = "${weather.temp}" + "°C"
            binding.txDes.text = "습도: ${weather.humidity}% \n풍속: ${weather.windspeed}m/s \n강수 확률: ${weather.rainPer}%"
            binding.txDes2.text = "${weather.fcstTime[0]}${weather.fcstTime[1]}" + ":" + "${weather.fcstTime[2]}${weather.fcstTime[3]}" + "\n${skyname}"
        }
}