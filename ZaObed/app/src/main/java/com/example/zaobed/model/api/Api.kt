package com.example.zaobed.model.api

import com.example.zaobed.model.response.Order
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("people")
    fun getAllPeople(): Call<List<Order>>
}