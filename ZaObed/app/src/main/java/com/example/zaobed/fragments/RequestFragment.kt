package com.example.zaobed.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.*
import com.example.zaobed.activity.MainActivity
import com.example.zaobed.R
import com.example.zaobed.model.response.OrderDada
import com.example.zaobed.presenter.OrdersPresenter
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class RequestFragment: Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    var name: String = ""

    val presenter = OrdersPresenter()


//    override fun onStart() {
//        super.onStart()
//        presenter.bindView(this, context!!)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        presenter.unbindView()
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_request, container, false)
        val button: Button = view.findViewById(R.id.buttonSend)
        val spinner: Spinner = view.findViewById(R.id.spinner)

        (activity as MainActivity).supportActionBar!!.title = "Создать заказ"
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        val itemList = arrayOf("Поставщик Василий", "Поставщик Виталий", "Поставщик Володя")
        val arrayAdapter = ArrayAdapter(context!!, R.layout.spinner_item, itemList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        button.setOnClickListener{

//            val productsList: List<OrderDada> = listOf(
//                OrderDada("Морковка", 20, ""),
//                OrderDada("Капуста", 20, ""),
//                OrderDada("Горох", 2000, ""),
//                OrderDada("Свекла", 20, "")
//            )

            val sdf = SimpleDateFormat("hh:mm: dd/mm/yyyy", Locale.ENGLISH)
            val currentDate = sdf.format(Date())

            val json = JSONObject()
            json.put("name", name)
            json.put("date", currentDate)
            json.put("status", "В обработке")
            //json.put("products", productsList)

            val presenter = OrdersPresenter()
            //presenter.postOrder(json, context!!)

            val ordersFragment = OrdersFragment()
            val activity = view.context as AppCompatActivity


            activity.getSupportFragmentManager().beginTransaction().replace(
                R.id.fragment_container,
                ordersFragment
            ).addToBackStack(null).commit()
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(arg0: AdapterView<*>, arg1: View?, position: Int, id: Long) {
                when (position) {
                    0 -> name = itemList[0]
                    1 -> name = itemList[1]
                    2 -> name = itemList[2]
                }
            }

            override fun onNothingSelected(arg0: AdapterView<*>) {
            }
        }

        return view
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        menu!!.findItem(R.id.action_fav).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }
}
