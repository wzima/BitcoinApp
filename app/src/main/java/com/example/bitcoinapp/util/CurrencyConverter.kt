package com.example.bitcoinapp.util

class CurrencyConverter {
    companion object {
        fun convert(value: Float, rate: Float) = value / rate
    }
}