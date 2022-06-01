package com.example.bitcoinapp.presentation.price_listings

sealed class BitcoinPriceListingsEvent {
    object Refresh : BitcoinPriceListingsEvent()
}
