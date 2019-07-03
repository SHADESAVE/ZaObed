package com.example.zaobed

import android.content.Context
import android.graphics.Color
import android.graphics.ColorSpace
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.EventLogTags
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.zaobed.model.response.OrderDada
import com.example.zaobed.model.response.OrdersData
import kotlinx.android.synthetic.main.recycler_view_order.view.*

class OrdersAdapter(): RecyclerView.Adapter<OrdersHolder>() {

//    private val productList: List<OrderDada> = listOf()
//    private val orders: MutableList<OrdersData> = mutableListOf(OrdersData(2, "Vasya", productList))
    private val orders: MutableList<OrdersData> = mutableListOf()

    fun setOrders(ordersList: List<OrdersData>) {
        this.orders.clear()
        this.orders.addAll(ordersList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): OrdersHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_order, parent, false)
        return OrdersHolder(itemView)
    }
    override fun getItemCount(): Int {
        return orders.size
    }
    override fun onBindViewHolder(holder: OrdersHolder, position: Int) {
        holder.bind(orders[position])
    }
}

class OrdersHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(ordersData: OrdersData) {
        itemView.textViewName.text = ordersData.name
        itemView.textViewDate.text = "Дата отправки: "+ordersData.date
        itemView.textViewStatus.text = ordersData.status
        var products: String = ""
        var value: String = ""
        var price: String = ""
        for (i in 0 until ordersData.products.size) {
            products += ordersData.products[i].product + "\n"
            value += ordersData.products[i].value.toString() + "шт\n"
            price += ordersData.products[i].price + "руб\n"
        }
        itemView.productsText.text = products
        itemView.valueText.text = value
        if (ordersData.status.equals("Принят")) {
            itemView.priceText.text = price
            itemView.textViewStatus.setTextColor(Color.rgb(5, 166, 45))
        }
        else {
            itemView.priceText.text = ""
            itemView.textViewStatus.setTextColor(Color.rgb(207, 6, 6))
        }
    }
}