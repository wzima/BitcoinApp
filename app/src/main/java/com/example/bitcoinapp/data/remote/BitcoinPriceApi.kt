package com.example.bitcoinapp.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface BitcoinPriceApi {

    @GET("ticker")
    suspend fun getListings(
    ): Response<Map<String, CurrencyValuesJSONModel>>

    companion object {
        //        const val API_KEY = "9CIDNW96CNEIGQF7"
        const val BASE_URL = "https://blockchain.info"
    }
}