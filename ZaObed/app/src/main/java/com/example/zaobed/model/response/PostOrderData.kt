package com.example.zaobed.model.response

data class PostOrderData (
    val id: Long,
    val price: Double,
    val status: Boolean,
    val date: String,
    val products: String
)