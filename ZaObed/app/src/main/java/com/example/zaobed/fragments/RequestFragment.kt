package com.example.zaobed.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.widget.*
import com.example.zaobed.Item
import com.example.zaobed.R
import com.example.zaobed.RecyclerViewAdapter2
import com.example.zaobed.activity.MainActivity
import com.example.zaobed.presenter.RequestPresenter
import com.example.zaobed.presenter.RequestView
import kotlinx.android.synthetic.main.fragment_request_2.*
import kotlinx.android.synthetic.main.fragment_request_2.view.*

const val EMPTY_STRING = ""

class RequestFragment: Fragment(), RequestView{

    private val presenter = RequestPresenter()
    private val adapter = RecyclerViewAdapter2()
    var idN: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onPrepareOptionsMenu(menu: Menu?) {
        menu!!.findItem(R.id.action_fav).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_request_2, container, false)
        val spinnerList = arrayOf("Василий", "Виталий", "Володя")
        val arrayAdapter = ArrayAdapter(context!!, R.layout.spinner_item, spinnerList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        presenter.bindView(this)

        (activity as MainActivity).supportActionBar!!.title = "Оформить заказ"
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        view.recview.layoutManager = LinearLayoutManager(context)
        view.recview.adapter = adapter

        view.spinner2.adapter = arrayAdapter
        view.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(arg0: AdapterView<*>, arg1: View?, position: Int, id: Long) {
                when (position) {
                    0 -> idN = 0
                    1 -> idN = 1
                    2 -> idN = 2
                }
            }
            override fun onNothingSelected(arg0: AdapterView<*>) {
            }
        }
        view.button.setOnClickListener{
            presenter.addItemOnClick()
        }
        view.button2.setOnClickListener{
            presenter.addPostOrder()
        }
        return view
    }

    override fun changeFragment() {
        activity!!.supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            MainTabFragment()
        ).addToBackStack(null).commit()
    }
    override fun showMessage(message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG
        ).show()
    }
    override fun addItem() {
        when {
            view!!.editText.text.isEmpty() or view!!.editText3.text.isEmpty() -> {
                view!!.textViewAlert1.text = "- Все поля должны быть заполнены"
            }
            else -> {
                view!!.textViewAlert1.text = EMPTY_STRING
                adapter.addString(Item(editText.text.toString(),editText3.text.toString()))
                view!!.editText.setText(EMPTY_STRING)
                view!!.editText3.setText(EMPTY_STRING)
            }
        }
    }
    override fun addPostOrder() {
        var productToString: String = EMPTY_STRING
        if (adapter.itemList.size == 0)
            view!!.textViewAlert1.text = "- Нельзя отправить пустой список"
        else {
            for (i in 0 until adapter.itemList.size) {
                productToString += "${adapter.itemList[i].name} ${adapter.itemList[i].count}\n"
            }
            Log.d("list", productToString)
            presenter.postOrder(idN, productToString)
            adapter.clear()
        }
    }
}