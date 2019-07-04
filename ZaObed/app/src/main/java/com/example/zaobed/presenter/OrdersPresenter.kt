package com.example.zaobed.presenter

import android.content.Context
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import com.example.zaobed.R
import com.example.zaobed.model.response.GetTestData
import com.example.zaobed.model.response.OrderDada
import com.example.zaobed.model.response.OrdersData
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OrdersPresenter() {

    private lateinit var view: OrdersView

    fun bindView(view: OrdersView) {
        this.view = view
        postOrder()
    }

    private fun updateOrders() {
        val app = App()
        app
            .create()
            .getAllOrders()
            .enqueue(object : Callback<List<GetTestData>> {
                override fun onFailure(call: Call<List<GetTestData>>, t: Throwable) {
                }

                override fun onResponse(call: Call<List<GetTestData>>, response: Response<List<GetTestData>>) {
                    val ordersList = response.body()
                    view.showOrders(ordersList!!)
                }
            })
    }

    fun postOrder() {
        val test : String = "{\"name\":\"Поставщик Вася\",\"date\":\"тут дата\",\"status\":\"отправлено\"}"
        val requestBody: RequestBody = RequestBody.create(MediaType.parse("application/json"), test.toString())
        Log.d("for post: ", "$requestBody")

        val gson = Gson()
        val test2 = gson.fromJson(test, OrdersData::class.java)
        Log.d("for post: ", "$test2")

        val app = App()
        app
            .create()
            .postOrder(requestBody)
            .enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                }
        })
    }

    fun onClickFab() {
        view.changeFragment()
    }
}