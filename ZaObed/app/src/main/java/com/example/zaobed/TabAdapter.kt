package com.example.zaobed

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zaobed.model.response.GetOrdersData
import kotlinx.android.synthetic.main.recycler_view_order.view.*

class TabAdapter(): RecyclerView.Adapter<MyHolder>() {
    lateinit var context: Context
    private val orders: MutableList<GetOrdersData> = mutableListOf()
    fun setOrders(ordersList: List<GetOrdersData>, contex: Context) {
        this.orders.clear()
        this.orders.addAll(ordersList)
        this.context = contex
        Log.d("ordersList: ", "$context")
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.recycler_view_order, p0, false)
        return MyHolder(itemView)
    }
    override fun getItemCount(): Int = orders.size
    override fun onBindViewHolder(p0: MyHolder, p1: Int) {
        p0.bind(orders[p1])
    }
}
class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(ordersData: GetOrdersData) {
        itemView.textViewName.text = "Поставщик 1"
        itemView.textViewDate.text = "Дата отправки: 12:22 13.02.2019"
        //itemView.textViewStatus.text = "В обработке"
        val products: String = EMPTY_SPACE
        val value: String = EMPTY_SPACE
        val price: String = EMPTY_SPACE
//        for (i in 0 until ordersData.products.size) {
//            products += ordersData.products[i].product + "\n"
//            value += ordersData.products[i].value.toString() + "шт\n"
//            price += ordersData.products[i].price + "руб\n"
//        }
        itemView.productsText.text = products
        itemView.valueText.text = value
        if (ordersData.status) {
            itemView.textViewStatus.text = "Принят"
            itemView.textViewStatus.setTextColor(Color.rgb(5, 166, 45))
        } else {
            itemView.textViewStatus.text = "В обработке"
            itemView.textViewStatus.setTextColor(Color.rgb(207, 6, 6))
        }

        if (itemView.textViewStatus.text == "Принят") {
//        if (ordersData.status.equals("Принят")) {
            itemView.priceText.text = price
            itemView.textViewStatus.setTextColor(Color.rgb(5, 166, 45))
        }
        else {
            itemView.priceText.text = EMPTY_SPACE
            itemView.textViewStatus.setTextColor(Color.rgb(207, 6, 6))
        }
    }
}