package com.example.bitcoinapp

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.activityViewModels
import com.example.bitcoinapp.databinding.ActivityMainBinding
import com.example.bitcoinapp.presentation.ui.MyBitcoinsDialogFragment
import com.example.bitcoinapp.presentation.ui.MyBitcoinsDialogFragment.Companion.MY_BITCOIN_PREF_NAME
import com.example.bitcoinapp.presentation.viewmodels.BitcoinPriceListingsViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    val viewModel: BitcoinPriceListingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // using toolbar as ActionBar
        setSupportActionBar(binding.toolbar)

        createTabs()

        if (!viewModel.isInitialized) {
            val sharedPrefs =
                getSharedPreferences(MyBitcoinsDialogFragment.PREFS_NAME, Context.MODE_PRIVATE)
            val myBitcoins = sharedPrefs.getFloat(MY_BITCOIN_PREF_NAME, 0f)
            viewModel.myBitcoins.value = myBitcoins
            viewModel.myBitcoins.observeForever {
                sharedPrefs.edit().putFloat(MY_BITCOIN_PREF_NAME, it).apply()
            }
        }

    }

    private fun createTabs() {
        // Tabs Customization
        binding.tabLayout.setSelectedTabIndicatorColor(Color.WHITE)
        binding.tabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_blue))
        binding.tabLayout.tabTextColors =
            ContextCompat.getColorStateList(this, android.R.color.white)

        // Set different Text Color for Tabs for when are selected or not
        //tab_layout.setTabTextColors(R.color.normalTabTextColor, R.color.selectedTabTextColor)

        // Number Of Tabs
        val numberOfTabs = 3

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
