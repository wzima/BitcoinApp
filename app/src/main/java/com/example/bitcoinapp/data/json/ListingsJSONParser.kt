package com.example.bitcoinapp.data.json

import com.example.bitcoinapp.data.remote.BitcoinPriceChartValuesJSONModel
import com.example.bitcoinapp.data.remote.CurrencyValuesJSONModel
import retrofit2.Response

interface ListingsJSONParser<T> {
    suspend fun parseCurrencyValues(response: Response<Map<String, CurrencyValuesJSONModel>>): List<T>

}