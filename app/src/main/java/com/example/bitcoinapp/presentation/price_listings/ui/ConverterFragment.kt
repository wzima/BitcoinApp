package com.example.bitcoinapp.presentation.price_listings.ui

import android.os.Bundle
import android.text.method.DigitsKeyListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bitcoinapp.R
import com.example.bitcoinapp.databinding.ConverterFragmentBinding
import com.example.bitcoinapp.domain.model.BitcoinPriceListing
import com.example.bitcoinapp.presentation.price_listings.BitcoinPriceListingsViewModel
import com.example.bitcoinapp.util.CurrencyConverter
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormatSymbols


@AndroidEntryPoint
class ConverterFragment : Fragment(), AdapterView.OnItemSelectedListener {
    lateinit var binding: ConverterFragmentBinding
    val viewModel: BitcoinPriceListingsViewModel by viewModels()
    var bitcoinPriceListings: List<BitcoinPriceListing>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ConverterFragmentBinding.inflate(inflater, container, false)

        binding.spinner.onItemSelectedListener = this

        val sep = DecimalFormatSymbols.getInstance().decimalSeparator
        binding.etUserValue.keyListener = DigitsKeyListener.getInstance("0123456789,")

        viewModel.allBitcoinPrices.observe(viewLifecycleOwner) { listings ->
            bitcoinPriceListings = listings
            updateFields()
        }


        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.currency_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        }

        return binding.root
    }

    private fun updateFields() {
        binding.etUserValue.text.toString().toFloatOrNull()?.let { userValue ->
            val selectedCurrency = binding.spinner.selectedItem as String
            val bitcoinPriceListing = bitcoinPriceListings?.find {
                it.currency == selectedCurrency
            }

            bitcoinPriceListing?.let { bitcoinPriceListing ->
                val rate = bitcoinPriceListing.price
                val priceInBitcoins = CurrencyConverter.convert(userValue, rate)
                binding.currency = priceInBitcoins

            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        updateFields()

    }


    override fun onNothingSelected(parent: AdapterView<*>) {
    }

}
