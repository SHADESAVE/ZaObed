package com.example.zaobed

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentManager

class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    private val listFragment = mutableListOf<Fragment>()
    private val listTitles = mutableListOf<String>()

    override fun getItem(p0: Int): Fragment = listFragment[p0]
    override fun getCount(): Int = listTitles.size
    override fun getPageTitle(position: Int): CharSequence? = listTitles[position]

    fun addFragment(fragment: Fragment, title: String) {
        listFragment.add(fragment)
        listTitles.add(title)
        notifyDataSetChanged()
    }
}