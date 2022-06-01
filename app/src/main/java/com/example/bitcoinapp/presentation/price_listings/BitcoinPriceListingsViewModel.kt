package com.example.bitcoinapp.presentation.price_listings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
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

    //var state by mutableStateOf(BitcoinPriceListingsState())
    //list of all comics in favorite database
    lateinit var allBitcoinPrices: LiveData<List<BitcoinPriceListing>>

    init {
        getBitcoinPriceListings()
    }

    fun onEvent(event: BitcoinPriceListingsEvent) {
        when (event) {
            is BitcoinPriceListingsEvent.Refresh -> {
                getBitcoinPriceListings(fetchFromRemote = true)
            }
        }
    }

    fun getBitcoinPriceListings(
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getPriceListings(fetchFromRemote)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                allBitcoinPrices = liveData { listings }
                            }
                        }
                        is Resource.Error -> {
                            Unit
                        }
                        is Resource.Loading -> {
                            Unit
                        }
                    }
                }
        }
    }
}