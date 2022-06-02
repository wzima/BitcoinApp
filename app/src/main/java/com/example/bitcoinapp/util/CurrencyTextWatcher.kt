package com.example.bitcoinapp.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.example.bitcoinapp.presentation.viewmodels.BitcoinPriceListingsViewModel

class CurrencyTextWatcher(val editText: EditText, val viewModel: BitcoinPriceListingsViewModel) :
    TextWatcher {

    override fun onTextChanged(cs: CharSequence?, start: Int, before: Int, count: Int) {
        if (!editText.hasFocus())
            return

        val value = NumberFormatParser.getNumber(editText.text.toString())
        viewModel.userCurrencyValue.value = value
    }

    override fun afterTextChanged(editable: Editable?) {
    }

    override fun beforeTextChanged(cs: CharSequence?, start: Int, before: Int, count: Int) {
    }

}