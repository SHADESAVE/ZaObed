package com.example.zaobed.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zaobed.App
import com.example.zaobed.R
import com.example.zaobed.OrdersAdapter
import com.example.zaobed.model.response.OrderDada
import com.example.zaobed.model.response.OrdersData
import com.example.zaobed.presenter.OrdersPresenter
import com.example.zaobed.presenter.OrdersView


class OrdersFragment: Fragment(), OrdersView{

    private val adapter = OrdersAdapter()
//    val presenter = OrdersPresenter(context!!)
    val presenter = OrdersPresenter()


    override fun onStart() {
        super.onStart()
        presenter.bindView(this, context!!)
    }

    override fun onStop() {
        super.onStop()
        presenter.unbindView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_orders, container, false)
        val fab: View = view.findViewById(R.id.fab)
        val recyclerView: RecyclerView = view.findViewById(R.id.order_recycler_view) as RecyclerView
//        val swipeRefresh: SwipeRefreshLayout = view.findViewById(R.id.swipe_container)


        fab.setOnClickListener { _ ->
            activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                RequestFragment()
            ).addToBackStack(null).commit()
        }
//
//        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_dark)
//        swipeRefresh.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
//            //presenter.bindView(this)
//            swipeRefresh.setRefreshing(false)
//        })

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


        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        return view
    }

    override fun showOrders(ordersList: List<OrdersData>) {
        adapter.setOrders(ordersList)
    }
}