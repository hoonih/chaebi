package com.example.chaebi.ui.main.weather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chaebi.data.model.ModelWeather
import com.example.chaebi.databinding.ItemSunBinding
import com.example.chaebi.ui.main.weather.adapter.viewholder.WeatherViewHolder

class WeatherAdapter(context: Context) : RecyclerView.Adapter<WeatherViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }

    // 임시의 빈 리스트
    private var weatherlist: Array<ModelWeather> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemSunBinding.inflate(inflater, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.onBind(weatherlist[position])
    }

    override fun getItemCount() = weatherlist.size

    // 임시 리스트에 준비해둔 가짜 리스트를 연결하는 함수
    fun setFriendList(weatherlist: Array<ModelWeather>) {
        this.weatherlist = weatherlist
        notifyDataSetChanged()
    }
}