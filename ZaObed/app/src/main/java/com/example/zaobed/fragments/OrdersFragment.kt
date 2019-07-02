package com.example.zaobed.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zaobed.Order
import com.example.zaobed.R
import com.example.zaobed.RecyclerViewAdapter

class OrdersFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_orders, container, false)

        val fab: View = view.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                RequestFragment()
            ).addToBackStack(null).commit()
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.order_recycler_view) as RecyclerView
        recyclerView.setHasFixedSize(true)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && fab.isShown)
                    fab.visibility = View.INVISIBLE
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    fab.visibility = View.VISIBLE
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        //результат метода GET ORDER с сервера будем записывать в OrderList
        val orderList = arrayListOf<Order>()
        for (i in 0..20)
            orderList.add(
                Order(
                    "Поставщик Василий",
                    "Говядина 20шк 5000руб\nГорох 10уп 2000руб",
                    "19:20 13.04.2019",
                    "Принят"
                )
            )
        //

        val bundle: Bundle? = this.arguments
        if (bundle != null) {
            orderList.add(
                Order(
                    bundle.getString("name"),
                    bundle.getString("description") + " " + bundle.getString("value"),
                    "13:10 12.11.2019",
                    "В обработке"
                )
            )
        }
        
        val recyclerViewAdapter = RecyclerViewAdapter(orderList)
        recyclerView.adapter = recyclerViewAdapter

        val layoutManager = LinearLayoutManager(this.activity)
        recyclerView.layoutManager = layoutManager

        return view
    }
}