package com.example.chaebi.ui.main.cloth.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chaebi.R
import com.example.chaebi.data.model.ModelWeather
import com.example.chaebi.data.model.cloth
import com.example.chaebi.databinding.ItemClothBinding
import com.example.chaebi.databinding.ItemSunBinding
import com.example.chaebi.ui.main.weather.adapter.viewholder.WeatherViewHolder

class itemViewHolder (private val binding: ItemClothBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(Cloth: cloth) {
        binding.txCloth.text = Cloth.name

    }
}