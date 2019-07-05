package com.example.zaobed.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.zaobed.R
import com.example.zaobed.TabAdapter
import com.example.zaobed.ViewPagerAdapter
import com.example.zaobed.activity.MainActivity
import com.example.zaobed.model.response.GetOrdersData
import com.example.zaobed.presenter.OrdersPresenter
import com.example.zaobed.presenter.OrdersView
import kotlinx.android.synthetic.main.fragment_orders2.view.*

class MainTabFragment: Fragment(), OrdersView {
    private val presenter = OrdersPresenter()
    private val tabFragment1 = TabFragment1()
    private val tabFragment2 = TabFragment2()

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
        val view = inflater.inflate(R.layout.fragment_orders2, container, false)
        val adapter = ViewPagerAdapter(activity!!.supportFragmentManager)
        val fab: View = view.findViewById(R.id.fab_button)
        val builder = AlertDialog.Builder(context!!)
        builder.setView(R.layout.progress_dialog)
        builder.setCancelable(false)

        presenter.bindView(this)
        presenter.updateOrders(builder)

        (activity as MainActivity).supportActionBar!!.title = "Список заказов"
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        view.view_pager.adapter = adapter
        adapter.addFragment(tabFragment1, "В обработке")
        adapter.addFragment(tabFragment2, "Принятые")
        view.tab_layout_id.setupWithViewPager(view.view_pager)
        fab.setOnClickListener {
            presenter.onClickFab()
        }
        return view
    }
    override fun showOrders(ordersList: List<GetOrdersData>) {
        val trueList = mutableListOf<GetOrdersData>()
        val falseList = mutableListOf<GetOrdersData>()
        for (i in 0 until ordersList.size) {
            if (ordersList[i].status)
                trueList.add(ordersList[i])
            else
                falseList.add(ordersList[i])
        }
        tabFragment1.addList(falseList, context!!)
        tabFragment2.addList(trueList, context!!)
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