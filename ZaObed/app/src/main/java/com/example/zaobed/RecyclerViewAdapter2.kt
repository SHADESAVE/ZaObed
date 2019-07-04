package com.example.zaobed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_view_order_2.view.*

class RecyclerViewAdapter2(): RecyclerView.Adapter<ViewHolder>() {

    val itemList: MutableList<Item> = mutableListOf()

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(itemList[p1].name,itemList[p1].count)
    }

    override fun getItemCount() = itemList.size

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
            LayoutInflater.from(p0.context)
                .inflate(R.layout.recycler_view_order_2, p0, false)
                .let { ViewHolder(it) }

    fun addString(item: Item){
        itemList.add(0,item)
        notifyItemInserted(0)
    }

    fun clear(){
        itemList.clear()
        notifyDataSetChanged()
    }
}

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(name: String, count: String){
        itemView.textViewName.text = name
        itemView.textViewCount.text = count
    }
}