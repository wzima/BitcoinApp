package com.example.bitcoinapp.presentation.price_listings

import com.example.bitcoinapp.domain.model.BitcoinPriceListing

data class BitcoinPriceListingsState(
    val bitcoinPrices: List<BitcoinPriceListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
)
