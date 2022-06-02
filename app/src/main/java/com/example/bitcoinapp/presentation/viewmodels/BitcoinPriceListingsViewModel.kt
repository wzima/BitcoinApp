package com.example.bitcoinapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.bitcoinapp.domain.model.BitcoinPriceListing
import com.example.bitcoinapp.domain.repository.BitcoinPriceRepository
import com.example.bitcoinapp.util.CurrencyConverter
import com.example.bitcoinapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

@HiltViewModel
class BitcoinPriceListingsViewModel @Inject constructor(private val repository: BitcoinPriceRepository) :
    ViewModel() {

    private val _currentBitcoinPrices = MutableLiveData<List<BitcoinPriceListing>>()
    val allBitcoinPrices: LiveData<List<BitcoinPriceListing>>
        get() = _currentBitcoinPrices

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val selectedRate = MutableLiveData<String>()
    val userCurrencyValue = MutableLiveData<Float>()

    val myBitcoins = MutableLiveData<Float>()

    fun setMyBitcoins(value: Float) {
        myBitcoins.value = value
    }

    var isInitialized = false
    var bitcoinToEuroRate = MutableLiveData<Float>()

    init {
        getBitcoinPriceListings()
    }

    fun convertBitcoinToEuro(): Float {
        return bitcoinToEuroRate.value?.let { rate ->
            myBitcoins.value?.let { myBitcoins ->
                CurrencyConverter.convert(
                    myBitcoins,
                    rate
                )
            } ?: 0f
        } ?: 0f
    }

    fun convertToBitcoin(): Float {
        val selectedBitcoinPriceListing = _currentBitcoinPrices.value?.find {
            it.currency == selectedRate.value
        }
        val rate = selectedBitcoinPriceListing?.price
        val value = userCurrencyValue.value ?: 0.0f

        return rate?.let { CurrencyConverter.convert(value, rate) } ?: 0.0f

    }

    private fun setBitcoinToEuroRate() {
        val selectedBitcoinPriceListing = _currentBitcoinPrices.value?.find {
            it.currency == SYMBOL_EUR
        }
        val rate = selectedBitcoinPriceListing?.price
        bitcoinToEuroRate.value = rate?.let { 1f / it } ?: 1.0f

    }

    fun getBitcoinPriceListings() {
        viewModelScope.launch {
            repository
                .getPriceListings()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            Log.d("getBitcoinPriceListings", "Resource.Success")
                            result.data?.let { listings ->
                                _currentBitcoinPrices.value = listings
                                setBitcoinToEuroRate()
                            }
                        }
                        is Resource.Error -> {
                            //show error message
                            Log.d("getBitcoinPriceListings", "Resource.Error")
                            Unit
                        }
                        is Resource.Loading -> {
                            //show loading
                            Log.d("getBitcoinPriceListings", "Resource.Loading")

                            _isLoading.value = result.isLoading
                            Unit
                        }
                    }
                }
        }
    }


    companion object {
        const val SYMBOL_EUR = "EUR"
    }
}