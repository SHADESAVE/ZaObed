package com.example.zaobed

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.zaobed.activity.MainActivity
import com.example.zaobed.fragments.OrdersFragment
import com.example.zaobed.presenter.OrdersPresenter
import kotlinx.android.synthetic.main.recycler_view_order.view.*
import kotlinx.android.synthetic.main.recycler_view_request.view.*
import kotlinx.android.synthetic.main.recycler_view_request.view.textViewName
import org.json.JSONObject
import java.nio.file.attribute.PosixFileAttributeView
import java.text.SimpleDateFormat
import java.util.*

class RecyclerViewAdapter2(): RecyclerView.Adapter<ViewHolder>() {
    val itemList: MutableList<Item> = mutableListOf()
    fun addString(item: Item){
        itemList.add(0,item)
        notifyItemInserted(0)
    }
    fun clear(){
        itemList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        LayoutInflater.from(p0.context)
            .inflate(R.layout.recycler_view_request, p0, false)
            .let { ViewHolder(it) }
    override fun getItemCount() = itemList.size
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(itemList[p1].name,itemList[p1].count,itemList.size)
    }
}
class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(name: String, count: String, position: Int){
        itemView.textViewName.text = name
        itemView.textViewCount.text = count
        if(position%2==0)
            itemView.constraint.setBackgroundColor(Color.rgb(215,215,215))
    }
}