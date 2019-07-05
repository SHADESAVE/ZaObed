package com.example.zaobed.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zaobed.R
import com.example.zaobed.TabAdapter
import com.example.zaobed.model.response.GetOrdersData
import kotlinx.android.synthetic.main.fragment_orders2.*
import kotlinx.android.synthetic.main.tab_layout_1.view.*

class TabFragment1: Fragment() {
    private val adapter = TabAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.tab_layout_1, container, false)

        view.rv_tab1.adapter = adapter
        view.rv_tab1.layoutManager = LinearLayoutManager(context)
        view.rv_tab1.setHasFixedSize(true)
//        view.rv_tab1.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                if (dy > 0 || dy < 0 && fab_button.isShown)
//                    fab.visibility = View.INVISIBLE
//            }
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                if (newState == RecyclerView.SCROLL_STATE_IDLE)
//                    fab.visibility = View.VISIBLE
//                super.onScrollStateChanged(recyclerView, newState)
//            }
//        })
//        fab.setOnClickListener {
//            presenter.onClickFab()
//        }
        return view
    }
    fun addList(list: List<GetOrdersData>, context: Context) {
        adapter.setOrders(list, context)
    }
}