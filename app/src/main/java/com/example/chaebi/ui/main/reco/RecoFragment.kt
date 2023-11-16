package com.example.chaebi.ui.main.reco

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chaebi.R
import com.example.chaebi.databinding.FragmentRecoBinding
import com.example.chaebi.databinding.FragmentWeatherBinding


class RecoFragment : Fragment() {
    private var _binding: FragmentRecoBinding? = null
    private val binding: FragmentRecoBinding
        get() = requireNotNull(_binding) {"바인딩 객체를 생성해주세요."}



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecoBinding.inflate(inflater, container, false)
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