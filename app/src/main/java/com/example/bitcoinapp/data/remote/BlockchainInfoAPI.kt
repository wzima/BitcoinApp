package com.example.bitcoinapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BlockchainInfoAPI {
    //https://blockchain.info/ticker


    @GET("ticker")
    suspend fun getCurrencyListings(
    ): Response<Map<String, CurrencyValuesJSONModel>>

    companion object {
        const val BASE_URL = "https://blockchain.info"
    }
}