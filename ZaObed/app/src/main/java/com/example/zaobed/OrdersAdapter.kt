package com.example.zaobed

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zaobed.model.response.GetOrdersData
import kotlinx.android.synthetic.main.recycler_view_order.view.*

const val EMPTY_SPACE = ""

class OrdersAdapter(): RecyclerView.Adapter<OrdersHolder>() {
    private val orders: MutableList<GetOrdersData> = mutableListOf()
    fun setOrders(ordersList: List<GetOrdersData>) {
        this.orders.clear()
        this.orders.addAll(ordersList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): OrdersHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_order, parent, false)
        return OrdersHolder(itemView)
    }
    override fun getItemCount() = orders.size
    override fun onBindViewHolder(holder: OrdersHolder, position: Int) {
        holder.bind(orders[position])
    }
}
class OrdersHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(ordersData: GetOrdersData) {
        itemView.textViewName.text = "Поставщик 1"
        itemView.textViewDate.text = "Дата отправки: 12:22 13.02.2019"
        itemView.textViewStatus.text = "В обработке"
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