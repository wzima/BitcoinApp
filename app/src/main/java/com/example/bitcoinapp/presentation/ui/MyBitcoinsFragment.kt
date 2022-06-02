package com.example.bitcoinapp.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.bitcoinapp.databinding.MybitcoinsFragmentBinding
import com.example.bitcoinapp.presentation.viewmodels.BitcoinPriceListingsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyBitcoinsFragment : Fragment() {
    lateinit var binding: MybitcoinsFragmentBinding
    val viewModel: BitcoinPriceListingsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MybitcoinsFragmentBinding.inflate(inflater, container, false)

        viewModel.myBitcoins.observe(viewLifecycleOwner)
        {
            binding.myBitcoins = it
            binding.myBitcoinsInEuro = viewModel.convertBitcoinToEuro()

            if (it == 0f) {
                binding.layoutNoBitcoins.visibility = View.VISIBLE
                binding.layoutMyBitcoins.visibility = View.GONE
            } else {
                binding.layoutNoBitcoins.visibility = View.GONE
                binding.layoutMyBitcoins.visibility = View.VISIBLE
            }
        }

        return binding.root
    }
}
