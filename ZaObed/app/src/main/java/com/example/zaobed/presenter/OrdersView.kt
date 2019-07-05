package com.example.zaobed.presenter

import com.example.zaobed.model.response.GetOrdersData

interface OrdersView {
    fun showOrders(ordersList: List<GetOrdersData>)
    fun changeFragment()
    fun showMessage(message: String)
}