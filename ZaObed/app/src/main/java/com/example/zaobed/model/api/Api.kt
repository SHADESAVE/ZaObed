package com.example.zaobed.model.api

import com.example.zaobed.model.response.GetTestData
import com.example.zaobed.model.response.OrdersData
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("/orders")
    fun getAllOrders(): Call<List<GetTestData>>

    @POST("/add")
    fun postOrder(@Body request: RequestBody): Call<ResponseBody>
}