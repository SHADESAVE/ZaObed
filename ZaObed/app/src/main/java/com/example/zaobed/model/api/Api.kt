package com.example.zaobed.model.api

import com.example.zaobed.model.response.OrdersData
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("getorders")
    fun getAllOrders(): Call<List<OrdersData>>

    @POST("postorder")
    fun postOrder(@Body request: RequestBody): Call<Boolean>
}