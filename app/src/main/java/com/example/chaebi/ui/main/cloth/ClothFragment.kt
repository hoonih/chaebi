package com.example.chaebi.ui.main.cloth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chaebi.R
import com.example.chaebi.databinding.FragmentClothBinding
import com.example.chaebi.databinding.FragmentRecoBinding


class ClothFragment : Fragment() {

    private var _binding: FragmentClothBinding? = null
    private val binding: FragmentClothBinding
        get() = requireNotNull(_binding) {"바인딩 객체를 생성해주세요."}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClothBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}