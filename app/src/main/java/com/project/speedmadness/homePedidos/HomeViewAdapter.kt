package com.project.speedmadness.homePedidos

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
/*
Adaptador para la TAB de los 2 fragment que hay por ahora
 */
class HomeViewAdapter (fr : FragmentActivity) : FragmentStateAdapter(fr) {

    private var listFragmnet : MutableList<Fragment> = ArrayList<Fragment>()
    private var fragmentTitle : MutableList<String> = ArrayList<String>()

    override fun getItemCount(): Int {
        return listFragmnet.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        listFragmnet.add(fragment)
        fragmentTitle.add(title)
    }

    override fun createFragment(position: Int) : Fragment{
        return listFragmnet.get(position)
    }

    fun getPageTitle(position: Int) : String {
        return fragmentTitle.get(position)
    }
}