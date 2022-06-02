package com.example.bitcoinapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BlockchainChartAPI {

    //https://api.blockchain.info/charts/market-price?timespan=52weeks&format=json

    @GET("charts/market-price")
    suspend fun getBitcoinChartValues(
        @Query("timespan") timespan: String = "52weeks",
        @Query("format") format: String = "json",
    ): Response<BitcoinPriceChartValuesJSONModel>

    companion object {
        const val BASE_URL = "https://api.blockchain.info"
    }
}