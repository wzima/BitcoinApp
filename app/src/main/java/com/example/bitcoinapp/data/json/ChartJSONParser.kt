package com.example.bitcoinapp.data.json

import com.example.bitcoinapp.data.remote.BitcoinPriceChartValuesJSONModel
import retrofit2.Response

interface ChartJSONParser<T> {
    suspend fun parse(response: Response<BitcoinPriceChartValuesJSONModel>): T
}