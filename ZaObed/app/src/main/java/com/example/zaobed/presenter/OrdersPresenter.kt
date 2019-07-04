package com.example.zaobed.presenter

import android.content.Context
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import com.example.zaobed.App
import com.example.zaobed.R
import com.example.zaobed.model.api.Api
import com.example.zaobed.model.response.GetTestData
import com.example.zaobed.model.response.OrderDada
import com.example.zaobed.model.response.OrdersData
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.xml.datatype.DatatypeConstants.SECONDS
import java.util.concurrent.TimeUnit


class OrdersPresenter() {

    private var view: OrdersView? = null
    private var context: Context? = null

    fun bindView(view: OrdersView, context: Context) {
        this.context = context
        this.view = view
        updateOrders2()
    }

    private fun updateOrders() {

        val dialog = createDialog()
        dialog.show()

        val app = App()
        app.create()
            .getAllOrders()
            .enqueue(object : Callback<List<OrdersData>> {
                override fun onFailure(call: Call<List<OrdersData>>, t: Throwable) {

                    dialog.dismiss()
                    val toast = Toast.makeText(
                        context,
                        "Ошибка соединения с сервером.", Toast.LENGTH_SHORT
                    )
                    toast.show()
                }

                override fun onResponse(call: Call<List<OrdersData>>, response: Response<List<OrdersData>>) {
                    val ordersList = response.body()

                    if (ordersList != null) {
                        //view?.showOrders(ordersList!!)
                    }
                    dialog.dismiss()
                }
            })
    }

    private fun updateOrders2() {

        val dialog = createDialog()
        dialog.show()

        val app = App()
        app.create()
            .getAllOrders2()
            .enqueue(object : Callback<List<GetTestData>> {
                override fun onFailure(call: Call<List<GetTestData>>, t: Throwable) {

                    dialog.dismiss()
                    val toast = Toast.makeText(
                        context,
                        "Ошибка соединения с сервером.", Toast.LENGTH_SHORT
                    )
                    toast.show()
                }

                override fun onResponse(call: Call<List<GetTestData>>, response: Response<List<GetTestData>>) {
                    val ordersList = response.body()
                    //view?.showOrders(ordersList!!)
                    dialog.dismiss()
                }
            })
    }

    private fun updaOrd() {
        val productList4: List<OrderDada> = listOf(
            OrderDada("То-Чего-Нет", 20, ""),
            OrderDada("То-Что-Забыто", 10, ""),
            OrderDada("То-Откуда-Неизвестно", 1, ""),
            OrderDada("То-Зачем-Непонятно", 1000000, ""),
            OrderDada("АААААААААААААААААААААААААА, спасите меня, меня заставляют кодить ночью),", 99999999, "99999999999999")
        )
        val productList1: List<OrderDada> = listOf(
            OrderDada("Морковка", 20, ""),
            OrderDada("Помидор", 10, ""),
            OrderDada("Тыква", 3, ""),
            OrderDada("Горох", 10000, "")
        )
        val productList2: List<OrderDada> = listOf(
            OrderDada("Говядина", 20, "20000"),
            OrderDada("Свинятина", 10, "13000"),
            OrderDada("Курятина", 13, "10500"),
            OrderDada("Индюшатина", 7, "10000")
        )
        val productList3: List<OrderDada> = listOf(
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, ""),
            OrderDada("Продукт", 1, "")
        )
        val OrdersList: List<OrdersData> = listOf(
            OrdersData(1, "Поставщик Василий","13:40 04.12.2019","В обработке", productList1),
            OrdersData(2, "Поставщик Володя", "18:23 24.01.2020", "Принят", productList2),
            OrdersData(3, "Поставщик Продуктов", "11:10 04.03.2020", "В обработке", productList3),
            OrdersData(3, "Поставщик Продуктов", "11:10 04.03.2020", "В обработке", productList3),
            OrdersData(3, "Поставщик Продуктов", "11:10 04.03.2020", "В обработке", productList3),
            OrdersData(3, "Поставщик Продуктов", "11:10 04.03.2020", "В обработке", productList3),
            OrdersData(3, "Поставщик Продуктов", "11:10 04.03.2020", "В обработке", productList3),
            OrdersData(3, "Поставщик Продуктов", "11:10 04.03.2020", "В обработке", productList3),
            OrdersData(3, "Поставщик Продуктов", "11:10 04.03.2020", "В обработке", productList3),
            OrdersData(3, "Поставщик Продуктов", "11:10 04.03.2020", "В обработке", productList3),
            OrdersData(3, "Поставщик Продуктов", "11:10 04.03.2020", "В обработке", productList3),
            OrdersData(0, "Поставщик Которого-Нет-И-Не-Будет", "20:21 10.10.2020", "В обработке",productList4)
        )
        //view?.showOrders(OrdersList)
    }

    fun postOrder(jsonObject: JSONObject, context: Context) {
        this.context = context

//        val dialog = createDialog()
//        dialog.show()
//
//        try {
//            TimeUnit.SECONDS.sleep(10)
//        } catch (e: InterruptedException) {
//            e.printStackTrace()
//        }

        val requestBody: RequestBody = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString())
//        App.retrofit
//        .create(Api::class.java)
        val app = App()
        app.create()
        .postOrder(requestBody)
        .enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {

//                dialog.dismiss()
                val toast = Toast.makeText(
                    context,
                    "Ошибка отправки", Toast.LENGTH_SHORT
                )
                toast.show()
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
//
//                if (response.body()!!) {
//
//                } else {
//
//                }
//                dialog.dismiss()
                val toast = Toast.makeText(
                    context,
                    "Отправлено!", Toast.LENGTH_SHORT
                )
                toast.show()
            }
        })
    }

    fun unbindView() {
        this.view = null
        this.context = null
    }

    fun createDialog(): AlertDialog {
        val builder = AlertDialog.Builder(context!!)
        Log.d("test", "context: "+context)
        builder.setView(R.layout.progress_dialog)
        builder.setCancelable(false)
        val dialog = builder.create()
        return dialog
    }
}