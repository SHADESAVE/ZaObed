package com.example.zaobed.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.zaobed.activity.MainActivity
import com.example.zaobed.R
import com.example.zaobed.OrdersAdapter
import com.example.zaobed.model.response.GetOrdersData
import com.example.zaobed.presenter.OrdersPresenter
import com.example.zaobed.presenter.OrdersView
import kotlinx.android.synthetic.main.fragment_orders.*
import kotlinx.android.synthetic.main.fragment_orders.view.*


class OrdersFragment: Fragment(), OrdersView{

    private val adapter = OrdersAdapter()
    private val presenter = OrdersPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_fav -> {
            val builder = AlertDialog.Builder(context!!)
            builder.setView(R.layout.progress_dialog)
            builder.setCancelable(false)
            presenter.updateOrders(builder)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_orders, container, false)
        val fab: View = view.findViewById(R.id.fab_button)
        val builder = AlertDialog.Builder(context!!)
        builder.setView(R.layout.progress_dialog)
        builder.setCancelable(false)

        presenter.bindView(this)
        presenter.updateOrders(builder)

        (activity as MainActivity).supportActionBar!!.title = "Список заказов"
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        view.order_recycler_view.adapter = adapter
        view.order_recycler_view.layoutManager = LinearLayoutManager(context)
        view.order_recycler_view.setHasFixedSize(true)
        view.order_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && fab_button.isShown)
                    fab.visibility = View.INVISIBLE
            }
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    fab.visibility = View.VISIBLE
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        fab.setOnClickListener {
            presenter.onClickFab()
        }
        return view
    }

    override fun showOrders(ordersList: List<GetOrdersData>) {
        adapter.setOrders(ordersList)
    }
    override fun changeFragment() {
        activity!!.supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                RequestFragment()
        ).addToBackStack(null).commit()
    }
    override fun showMessage(message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG
        ).show()
    }
}
