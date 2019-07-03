package com.example.zaobed.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.zaobed.R

abstract class CreateOrderFragment: Fragment(), AdapterView.OnItemSelectedListener, View.OnClickListener {

    var name: String = ""
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_request_2, container, false)

        val button: Button = view.findViewById(R.id.button)
        val button2: Button = view.findViewById(R.id.button2)

        val spinner: Spinner = view.findViewById(R.id.spinner2)
        val itemList = arrayOf("Поставщик Василий", "Поставщик Виталий", "Поставщик Володя")

        button.setOnClickListener(this)
        spinner.setOnItemSelectedListener(this)

        val arrayAdapter = ArrayAdapter(context!!, R.layout.spinner_item, itemList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = arrayAdapter

        return view
    }

    override fun onClick(p0: View?) {
        val editText: EditText = view!!.findViewById(R.id.editText)
        val editText3: EditText = view!!.findViewById(R.id.editText3)
        val ordersFragment = OrdersFragment()
        val activity = view!!.context as AppCompatActivity

    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        // use position to know the selected item
        when (position) {
            0 -> name = "Поставщик Василий"
            1 -> name = "Поставщик Виталий"
            2 -> name = "Поставщик Володя"
        }
    }
    override fun onNothingSelected(arg0: AdapterView<*>) {
    }

}