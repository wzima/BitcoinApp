package com.example.bitcoinapp.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.example.bitcoinapp.presentation.price_listings.BitcoinPriceListingsViewModel

class CurrencyTextWatcher(val editText: EditText, val viewModel: BitcoinPriceListingsViewModel) :
    TextWatcher {

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (!editText.hasFocus())
            return

        val value = editText.text.toString().replace(",", ".").toFloatOrNull() ?: 0.0f
        viewModel.userCurrencyValue.value = value
    }

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(cs: CharSequence?, start: Int, before: Int, count: Int) {
    }

}