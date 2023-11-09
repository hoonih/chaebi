package com.example.chaebi.ui.login.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chaebi.R
import com.example.chaebi.databinding.FragmentNameBinding
import com.example.chaebi.databinding.FragmentRegisterBinding
import com.example.chaebi.ui.login.LoginActivity


class NameFragment : Fragment() {

    private var _binding: FragmentNameBinding? = null
    private val binding: FragmentNameBinding
        get() = requireNotNull(_binding) {"바인딩 객체를 생성해주세요."}



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBack.setOnClickListener {
            (activity as LoginActivity).replaceFragment(RegisterFragment())
        }
        binding.btLogin.setOnClickListener {
            if (binding.etName.text.toString().isNotEmpty()) {
                (activity as LoginActivity).savename(binding.etName.text.toString())
                (activity as LoginActivity).closeFragment(this)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}