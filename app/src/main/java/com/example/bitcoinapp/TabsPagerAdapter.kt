package com.example.bitcoinapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bitcoinapp.presentation.price_listings.ui.ConverterFragment
import com.example.bitcoinapp.presentation.price_listings.ui.DashboardFragment

class TabsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var numberOfTabs: Int) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                val bundle = Bundle()
                bundle.putString(FRAGMENT_NAME, "Dashboard Fragment")
                val dashboardFragment = DashboardFragment()
                dashboardFragment.arguments = bundle
                return dashboardFragment
            }
            1 -> {
                val bundle = Bundle()
                bundle.putString(FRAGMENT_NAME, "Converter Fragment")
                val converterFragment = ConverterFragment()
                converterFragment.arguments = bundle
                return converterFragment
            }
            2 -> {
                val bundle = Bundle()
                bundle.putString(FRAGMENT_NAME, "MyBitcoins Fragment")
                val myBitcoinsFragment = MyBitcoinsFragment()
                myBitcoinsFragment.arguments = bundle
                return myBitcoinsFragment
            }
            else -> return DashboardFragment()
        }
    }

    override fun getItemCount(): Int {
        return numberOfTabs
    }

    companion object {
        val FRAGMENT_NAME = "fragmentName"
    }
}