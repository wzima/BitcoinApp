package com.example.bitcoinapp.presentation.price_listings

import android.util.Log
import androidx.lifecycle.*
import com.example.bitcoinapp.domain.model.BitcoinPriceListing
import com.example.bitcoinapp.domain.repository.BitcoinPriceRepository
import com.example.bitcoinapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

@HiltViewModel
class ConverterViewModel @Inject constructor() :
    ViewModel() {

    private val _userInputValue = MutableLiveData<Float>()
    val userInputValue: LiveData<Float>
        get() = _userInputValue

    init {
    }

}