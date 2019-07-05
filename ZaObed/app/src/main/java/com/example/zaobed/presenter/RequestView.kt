package com.example.zaobed.presenter

import android.view.View

interface RequestView {
    fun changeFragment()
    fun showMessage(message: String)
    fun addItem()
    fun addPostOrder()
}