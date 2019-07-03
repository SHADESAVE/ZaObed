package com.example.zaobed.presenter

import com.example.zaobed.model.response.OrdersData

interface OrdersView {
    fun showOrders(ordersList: List<OrdersData>)
}