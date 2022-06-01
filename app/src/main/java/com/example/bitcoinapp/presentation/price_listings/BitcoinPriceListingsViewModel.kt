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
class BitcoinPriceListingsViewModel @Inject constructor(private val repository: BitcoinPriceRepository) :
    ViewModel() {

    private val _currentBitcoinPrices = MutableLiveData<List<BitcoinPriceListing>>()
    val allBitcoinPrices: LiveData<List<BitcoinPriceListing>>
        get() = _currentBitcoinPrices

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        getBitcoinPriceListings()
    }

    fun getBitcoinPriceListings() {
        viewModelScope.launch {
            repository
                .getPriceListings()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            Log.d("getBitcoinPriceListings","Resource.Success")
                            result.data?.let { listings ->
                                _currentBitcoinPrices.value = listings
                            }
                        }
                        is Resource.Error -> {
                            //show error message
                            Log.d("getBitcoinPriceListings","Resource.Error")
                            Unit
                        }
                        is Resource.Loading -> {
                            //show loading
                            Log.d("getBitcoinPriceListings","Resource.Loading")

                            _isLoading.value = result.isLoading
                            Unit
                        }
                    }
                }
        }
    }
}