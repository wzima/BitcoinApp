package com.example.bitcoinapp.data.json

import com.example.bitcoinapp.data.remote.CurrencyValuesJSONModel
import retrofit2.Response

interface JSONParser<T> {
    suspend fun parse(response: Response<Map<String, CurrencyValuesJSONModel>>): List<T>
}