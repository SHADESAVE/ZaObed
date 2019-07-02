package com.example.zaobed

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.recycler_view_order.view.*

class RecyclerViewAdapter(private val orderList: ArrayList<Order>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_order, parent, false)
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (orderList[position].status.equals("Принят"))
            holder.itemView.textViewStatus.setTextColor(Color.rgb(21, 144, 51))
        if (orderList[position].status.equals("В обработке"))
            holder.itemView.textViewStatus.setTextColor(Color.rgb(200, 51, 21))

        holder.itemView.textViewName.text = orderList[position].name
        holder.itemView.textViewDescription.text = orderList[position].description
        holder.itemView.textViewStatus.text = orderList[position].status
        holder.itemView.textViewDate.text = orderList[position].date
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var textViewName : TextView
        var textViewDescription: TextView
        var textViewStatus: TextView
        var textViewDate: TextView

        init {

            textViewName = itemView.findViewById(R.id.textViewName)
            textViewDescription = itemView.findViewById(R.id.textViewDescription)
            textViewStatus = itemView.findViewById(R.id.textViewStatus)
            textViewDate = itemView.findViewById(R.id.textViewDate)
        }
    }
}