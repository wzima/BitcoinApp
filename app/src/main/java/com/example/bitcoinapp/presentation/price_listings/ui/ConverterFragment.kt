package com.example.bitcoinapp.presentation.price_listings.ui

import android.os.Bundle
import android.text.method.DigitsKeyListener
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
import com.example.bitcoinapp.util.CurrencyTextWatcher
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


//        val sep = DecimalFormatSymbols.getInstance().decimalSeparator
//        binding.etUserValue.keyListener = DigitsKeyListener.getInstance("0123456789$sep")

        //set observers for viewmodel
        viewModel.selectedRate.observe(viewLifecycleOwner) {
            updateFields()
        }

        viewModel.userCurrencyValue.observe(viewLifecycleOwner) {
            updateFields()
        }

        viewModel.allBitcoinPrices.observe(viewLifecycleOwner) { listings ->
            bitcoinPriceListings = listings
            updateFields()
        }

        val watcher = CurrencyTextWatcher(binding.etUserValue, viewModel)
        binding.etUserValue.addTextChangedListener(watcher)

//set arrayadapter for spinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.currency_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        }
        binding.spinner.onItemSelectedListener = this

        return binding.root
    }

    private fun updateFields() {
        val userValue = binding.etUserValue.text.toString().toFloatOrNull()
        if (userValue != null) {
            binding.etUserValue.text.toString().toFloatOrNull()?.let { userValue -> }
            val bitcoinPrice = viewModel.convertToBitcoin()
            binding.currency = bitcoinPrice
        } else
            binding.currency = 0.0f
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        viewModel.selectedRate.value = parent.selectedItem.toString()
    }


    override fun onNothingSelected(parent: AdapterView<*>) {
    }

}
