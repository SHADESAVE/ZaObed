package com.example.zaobed

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class App {

    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://swapi.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}