package com.example.chaebi.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.chaebi.ui.main.MainActivity
import com.example.chaebi.R
import com.example.chaebi.databinding.ActivityLoginBinding
import com.example.chaebi.ui.login.fragment.RegisterFragment
import com.example.chaebi.ui.splash.SplashActivity
import com.example.chaebi.util.PopUpUtil

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private var registerstate = false
    private lateinit var id : String
    private lateinit var pw : String
    private lateinit var username : String

    fun savename(name: String) {
        username = name
    }
    fun saveemailpass(inputid: String, inputpw: String) {
        id = inputid
        pw = inputpw
    }
    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, fragment)
            .commit()
    }
    fun closeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .remove(fragment)
            .commit()
        registerstate = true
    }
    fun back() {
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
        finish()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btLogin.setOnClickListener {
            if (registerstate == true)
            {
                if ( binding.etEmail.text.toString() == id) {
                    if ( binding.etPassword.text.toString() == pw) {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("name", username)
                        startActivity(intent)
                        finish()
                    }
                    else
                        PopUpUtil(this).snackbar(binding.root, "비밀번호를 확인해주세요")
                }
                else
                    PopUpUtil(this).snackbar(binding.root, "이메일을 확인해주세요")

            }
            else {
                PopUpUtil(this).snackbar(binding.root, "회원가입을 해주세요")
            }
        }


        binding.fragment.bringToFront();

        binding.txRegister.setOnClickListener {
            replaceFragment(RegisterFragment())
        }
    }
}