package com.example.bitcoinapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.example.bitcoinapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // using toolbar as ActionBar
        setSupportActionBar(binding.toolbar)

        // Tabs Customization
        binding.tabLayout.setSelectedTabIndicatorColor(Color.WHITE)
        binding.tabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
        binding.tabLayout.tabTextColors =
            ContextCompat.getColorStateList(this, android.R.color.white)

        // Set different Text Color for Tabs for when are selected or not
        //tab_layout.setTabTextColors(R.color.normalTabTextColor, R.color.selectedTabTextColor)

        // Number Of Tabs
        val numberOfTabs = 2

        // Set Tabs in the center
        //tab_layout.tabGravity = TabLayout.GRAVITY_CENTER

        // Show all Tabs in screen
        binding.tabLayout.tabMode = TabLayout.MODE_FIXED

        // Scroll to see all Tabs
        //tab_layout.tabMode = TabLayout.MODE_SCROLLABLE

        // Set Tab icons next to the text, instead of above the text
        binding.tabLayout.isInlineLabel = true

        // Set the ViewPager Adapter
        val adapter = TabsPagerAdapter(supportFragmentManager, lifecycle, numberOfTabs)
        binding.tabsViewpager.adapter = adapter

        // Enable Swipe
        binding.tabsViewpager.isUserInputEnabled = true

        // Link the TabLayout and the ViewPager2 together and Set Text & Icons
        TabLayoutMediator(binding.tabLayout, binding.tabsViewpager) { tab, position ->
            when (position) {
                0 -> {
                    tab.setText(R.string.Dashboard)
                    tab.setIcon(R.drawable.ic_dashboard)
                }
                1 -> {
                    tab.setText(R.string.Converter)
                    tab.setIcon(R.drawable.ic_converter)

                }
                2 -> {
                    tab.setText(R.string.MyBitcoins)
                    tab.setIcon(R.drawable.ic_bitcoin)
                }

            }
            // Change color of the icons
            tab.icon?.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    Color.WHITE,
                    BlendModeCompat.SRC_ATOP
                )
        }.attach()


        setCustomTabTitles()

//        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab) {
//
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab) {
//
//            }
//        })
    }

    private fun setCustomTabTitles() {
        val vg = binding.tabLayout.getChildAt(0) as ViewGroup
        val tabsCount = vg.childCount

        for (j in 0 until tabsCount) {
            val vgTab = vg.getChildAt(j) as ViewGroup

            val tabChildCount = vgTab.childCount

            for (i in 0 until tabChildCount) {
                val tabViewChild = vgTab.getChildAt(i)
                if (tabViewChild is TextView) {

                    // Change Font and Size
                    tabViewChild.typeface = Typeface.DEFAULT_BOLD
                }
            }
        }
    }

}
