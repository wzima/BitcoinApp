package com.example.bitcoinapp.presentation.price_listings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bitcoinapp.R
import com.example.bitcoinapp.databinding.DashboardFragmentBinding
import com.example.bitcoinapp.presentation.price_listings.BitcoinPriceListingsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardFragment : Fragment() {
    lateinit var binding: DashboardFragmentBinding
    val viewModel: BitcoinPriceListingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DashboardFragmentBinding.inflate(inflater, container, false)

        binding.fragmentName.setText(R.string.Dashboard)

        viewModel.getBitcoinPriceListings()
        return binding.root
    }

}
