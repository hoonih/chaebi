package com.example.chaebi.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.chaebi.R
import com.example.chaebi.data.model.ITEM
import com.example.chaebi.data.model.ModelWeather
import com.example.chaebi.data.model.WEATHER
import com.example.chaebi.databinding.ActivityMainBinding
import com.example.chaebi.remote.RetrofitClient
import com.example.chaebi.remote.service.WeatherService
import com.example.chaebi.ui.main.cloth.ClothFragment
import com.example.chaebi.ui.main.reco.RecoFragment
import com.example.chaebi.ui.main.weather.WeatherFragment
import com.example.chaebi.ui.main.weather.adapter.WeatherAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickBottomNavigation()



    }

    private fun clickBottomNavigation() {
        binding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nv_weather-> {
                    replaceFragment(WeatherFragment())
                    true
                }

                R.id.nv_rec-> {
                    replaceFragment(RecoFragment())
                    true
                }

                R.id.nv_cloth-> {
                    replaceFragment(ClothFragment() )
                    true
                }
                else -> false
            }
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }
}