package com.example.chaebi.ui.login.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chaebi.databinding.FragmentRegisterBinding
import com.example.chaebi.ui.login.LoginActivity
import com.example.chaebi.util.PopUpUtil
import java.util.regex.Pattern


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding
        get() = requireNotNull(_binding) {"바인딩 객체를 생성해주세요."}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        binding.btNext.setOnClickListener {
            if (Pattern.matches(emailValidation, binding.etEmail.text.toString()))
                if (binding.etPassword.text.length >= 6)
                    if (binding.etPassword.text.toString() == binding.etPasswordre.text.toString()){
                        (activity as LoginActivity).saveemailpass(binding.etEmail.text.toString(), binding.etPassword.text.toString())
                        (activity as LoginActivity).replaceFragment(NameFragment())
                    }
                    else
                        PopUpUtil(LoginActivity()).snackbar(binding.root, "비밀번호가 일치하지 않습니다.")
                else
                    PopUpUtil(LoginActivity()).snackbar(binding.root, "비밀번호를 6글자 이상으로 설정해주세요")
            else
                PopUpUtil(LoginActivity()).snackbar(binding.root, "이메일 형식을 지켜주세요")
        }

        binding.ivBack.setOnClickListener {
            (activity as LoginActivity).back()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}