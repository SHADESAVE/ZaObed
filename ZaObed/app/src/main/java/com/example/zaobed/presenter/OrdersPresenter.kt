package com.example.zaobed.presenter

import android.support.v7.app.AlertDialog
import android.util.Log
import com.example.zaobed.App
import com.example.zaobed.model.api.Api
import com.example.zaobed.model.response.GetOrdersData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OrdersPresenter() {
    private lateinit var view: OrdersView
    fun bindView(view: OrdersView) {
        this.view = view
    }

    fun updateOrders(builder: AlertDialog.Builder) {
        val dialog = builder.create()
        dialog.show()

        App.retrofit
            .create(Api::class.java)
            .getAllOrders()
            .enqueue(object : Callback<List<GetOrdersData>> {
                override fun onFailure(call: Call<List<GetOrdersData>>, t: Throwable) {
                    dialog.dismiss()
                    //////////////////
                    val oList = listOf(
                        GetOrdersData("product1", false, 2),
                        GetOrdersData("product2", true, 2),
                        GetOrdersData("product3", true, 2),
                        GetOrdersData("product4", false, 2),
                        GetOrdersData("product5", false, 2)
                    )
                    view.showOrders(oList)
//////////////////
                    view.showMessage("Ошибка соединения с сервером")

                }
                override fun onResponse(call: Call<List<GetOrdersData>>, response: Response<List<GetOrdersData>>) {
                    dialog.dismiss()
                    val ordersList = response.body()
                    Log.d("ordersList", "$ordersList")
//////////////////
                    val oList = listOf(
                        GetOrdersData("product1", false, 2),
                        GetOrdersData("product2", true, 2),
                        GetOrdersData("product3", true, 2),
                        GetOrdersData("product4", false, 2),
                        GetOrdersData("product5", false, 2)
                        )
                    view.showOrders(oList)
//////////////////
                    if(ordersList != null)
                        view.showOrders(ordersList!!)
                }
            })
    }
    fun onClickFab() {
        view.changeFragment()
    }
}