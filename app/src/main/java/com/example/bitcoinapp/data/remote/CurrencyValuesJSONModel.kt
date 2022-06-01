package com.example.bitcoinapp.data.remote

import com.google.gson.annotations.SerializedName


data class CurrencyValuesJSONModel(
    @SerializedName("15m")
    var fiveteenMinutes: Float?,

    @SerializedName("last")
    var last: Float?,

    @SerializedName("buy")
    var buy: Float?,

    @SerializedName("sell")
    var sell: Float?,

    @SerializedName("symbol")
    var symbol: String?,
)


