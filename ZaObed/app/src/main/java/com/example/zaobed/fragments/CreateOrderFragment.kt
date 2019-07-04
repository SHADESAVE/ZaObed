package com.example.zaobed.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.zaobed.Item
import com.example.zaobed.R
import com.example.zaobed.RecyclerViewAdapter2
import kotlinx.android.synthetic.main.fragment_request_2.*

class CreateOrderFragment: Fragment(), AdapterView.OnItemSelectedListener{

    private val adapter = RecyclerViewAdapter2()
    var name: String = ""
    val EMPTYSTRING = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_request_2, container, false)

        val button: Button = view.findViewById(R.id.button)
        val button2: Button = view.findViewById(R.id.button2)

        val recyclerView: RecyclerView = view.findViewById(R.id.recview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        val spinner: Spinner = view.findViewById(R.id.spinner2)
        val itemList = arrayOf("Василий", "Виталий", "Володя")

        button.setOnClickListener{
            val itemName = editText.text
            val itemCount = editText3.text
            adapter.addString(Item(itemName.toString(),itemCount.toString()))
            editText.setText(EMPTYSTRING)
            editText3.setText(EMPTYSTRING)
        }
        button2.setOnClickListener{
            val itemList = adapter.itemList
            val trader = name
            adapter.clear()
        }
        spinner.onItemSelectedListener = this

        val arrayAdapter = ArrayAdapter(context!!, R.layout.spinner_item, itemList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = arrayAdapter

        return view
    }

//    override fun onClick(p0: View?) {
//        val editText: EditText = view!!.findViewById(R.id.editText)
//        val editText3: EditText = view!!.findViewById(R.id.editText3)
//        val ordersFragment = OrdersFragment()
//        val activity = view!!.context as AppCompatActivity
//
//    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        // use position to know the selected item
        when (position) {
            0 -> name = "Василий"
            1 -> name = "Виталий"
            2 -> name = "Володя"
        }
    }
    override fun onNothingSelected(arg0: AdapterView<*>) {
    }
}