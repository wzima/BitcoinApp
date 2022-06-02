package com.example.bitcoinapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.bitcoinapp.R
import com.example.bitcoinapp.databinding.MybitcoinsDialogFragmentBinding
import com.example.bitcoinapp.presentation.viewmodels.BitcoinPriceListingsViewModel
import com.example.bitcoinapp.util.NumberFormatParser
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyBitcoinsDialogFragment : DialogFragment() {
    lateinit var binding: MybitcoinsDialogFragmentBinding
    val viewModel: BitcoinPriceListingsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Theme_MaterialComponents_Light_Dialog_MinWidth)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MybitcoinsDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etUserValue.setText(viewModel.myBitcoins.value.toString())

        binding.buttonOk.setOnClickListener {
            val myBitcoinValue = NumberFormatParser.getNumber(binding.etUserValue.text.toString())
            viewModel.setMyBitcoins(myBitcoinValue)
            dismiss()
        }

        binding.buttonCancel.setOnClickListener { dismiss() }
        //reference layout elements by name freely
//        binding.tvReward.setOnClickListener { }
    }

    companion object {
        const val TAG = "MyBitcoinsDialogFragment"
        const val PREFS_NAME = "MyBitcoinsPreferences"
        const val MY_BITCOIN_PREF_NAME = "MyBitcoinsPreferences"
    }
}
