package com.example.zaobed.presenter

import com.example.zaobed.App
import com.example.zaobed.model.api.Api
import com.example.zaobed.model.response.Order
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersListPresenter {

    private var view: PersonsView? = null

    fun bindView(view: PersonsView) {
        this.view = view
        updatePersons()
    }

    private fun updateOrders() {

        App.retrofit
            .create(Api::class.java)
            .getAllPeople()
            .enqueue(object : Callback<List<Order>> {
                override fun onFailure(call: Call<List<Order>>, t: Throwable) {

                }
                override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                    val persons = response.body()?.results?.map { it.name }

                    if (persons != null) {
                        view?.showPersons(persons)
                    }
                }
            })
    }

    fun unbindView() {
        this.view = null
    }
}