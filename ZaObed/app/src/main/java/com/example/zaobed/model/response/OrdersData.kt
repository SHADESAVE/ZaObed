package com.example.zaobed.model.response

data class OrdersData (
    val id: Int,
    val name: String,
    val date: String,
    val status: String,
    val products: List<OrderDada>
)