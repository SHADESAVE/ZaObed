package com.example.zaobed.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.app.AppCompatActivity
import android.widget.*
import com.example.zaobed.R


class RequestFragment: Fragment(), AdapterView.OnItemSelectedListener, View.OnClickListener {

    var name: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_request, container, false)

        val button: Button = view.findViewById(R.id.buttonSend)
        button.setOnClickListener(this)

        val spinner: Spinner = view.findViewById(R.id.spinner)
        val itemList = arrayOf("Поставщик Василий", "Поставщик Виталий", "Поставщик Володя")

        spinner.setOnItemSelectedListener(this)

        val arrayAdapter = ArrayAdapter(context!!, R.layout.spinner_item, itemList)


        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = arrayAdapter

        return view
    }


    override fun onClick(v: View?) {

        val editText1: EditText = view!!.findViewById(R.id.editText1)
        val editText2: EditText = view!!.findViewById(R.id.editText2)

        val ordersFragment = OrdersFragment()
        val activity = view!!.context as AppCompatActivity

        //Функция отправки
        //на сервер

        activity.getSupportFragmentManager().beginTransaction().replace(
            R.id.fragment_container,
            ordersFragment
        ).addToBackStack(null).commit()

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
