package com.example.zaobed

import com.example.zaobed.model.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class App {
    fun create(): Api {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.12.216:8080/")
            .build()
        return retrofit.create(Api::class.java)
    }
//    companion object {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
}