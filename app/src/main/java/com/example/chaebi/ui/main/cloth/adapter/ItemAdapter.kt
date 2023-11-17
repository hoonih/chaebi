package com.example.chaebi.ui.main.cloth.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chaebi.data.model.ModelWeather
import com.example.chaebi.data.model.cloth
import com.example.chaebi.databinding.ItemClothBinding
import com.example.chaebi.databinding.ItemSunBinding
import com.example.chaebi.ui.main.cloth.adapter.viewholder.itemViewHolder

class ItemAdapter(context: Context) : RecyclerView.Adapter<itemViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }

    // 임시의 빈 리스트
    private var clothlist: Array<cloth> = emptyArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        val binding = ItemClothBinding.inflate(inflater, parent, false)
        return itemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        holder.onBind(clothlist[position])
    }

    override fun getItemCount() = clothlist.size

    // 임시 리스트에 준비해둔 가짜 리스트를 연결하는 함수
    fun setFriendList(clothlist: Array<cloth>) {
        this.clothlist = clothlist
        notifyDataSetChanged()
    }
}