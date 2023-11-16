package com.example.chaebi.remote.service

import com.example.chaebi.data.model.WEATHER
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("getVilageFcst?serviceKey=5YpfvrATh0LUmfBG7Ax7DbQry%2FCqcZyUElPuf27DpcWTss6lWLaVd0cQGfhDwNbOpuIgL%2BhXFQdBokIMzp97ow%3D%3D")
        fun GetWeather(@Query("numOfRows") num_of_rows : Int,   // 한 페이지 경과 수
                   @Query("pageNo") page_no : Int,          // 페이지 번호
                   @Query("dataType") data_type : String,   // 응답 자료 형식
                   @Query("base_date") base_date : String,  // 발표 일자
                   @Query("base_time") base_time : String,  // 발표 시각
                   @Query("nx") nx : String,                // 예보지점 X 좌표
                   @Query("ny") ny : String)                // 예보지점 Y 좌표
            : Call<WEATHER>
}