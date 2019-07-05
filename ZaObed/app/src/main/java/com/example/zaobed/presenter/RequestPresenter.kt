package com.example.zaobed.presenter

import android.util.Log
import com.example.zaobed.App
import com.example.zaobed.model.api.Api
import com.example.zaobed.model.response.PostOrderData
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

// Example of json
//        val rootObject= JSONObject()
//        rootObject.put("id",idN)
//        rootObject.put("price",-1)
//        rootObject.put("status",false)
//        rootObject.put("date", currentDate)
//        rootObject.put("products", productToString)
//        Log.d("json", "$rootObject")

//  Example from Json.toString to Gson
//        val gsons = Gson()
//        val test = "{\"name\":\"Поставщик Вася\",\"date\":\"тут дата\",\"status\":\"отправлено\"}"
//        val test2 = gsons.fromJson(test, PostOrderData::class.java)
//        Log.d("fromJsonToGson: ", "$test2, #test2.name")

class RequestPresenter() {
    private lateinit var view: RequestView
    fun bindView(view: RequestView) {
        this.view = view
    }

    fun postOrder(idN: Long, productToString: String) {
        val sdf = SimpleDateFormat("yyyy-mm-dd hh:mm")
        val currentDate = sdf.format(Date())

        val request = PostOrderData(idN, -1.0, false, currentDate, productToString)
        Log.d("post", "$request")
        App.retrofit
            .create(Api::class.java)
            .postOrder(request)
            .enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    view.showMessage("Ошибка отправки")
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    //view.showMessage("Отправлено")
                }
        })
        view.changeFragment()
    }
    fun addItemOnClick(){
        view.addItem()
    }
    fun addPostOrder() {
        view.addPostOrder()
    }
}