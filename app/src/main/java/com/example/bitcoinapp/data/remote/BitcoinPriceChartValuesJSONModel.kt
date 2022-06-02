package com.example.bitcoinapp.data.remote

import com.google.gson.annotations.SerializedName


data class BitcoinPriceChartValuesJSONModel(
    @SerializedName("status")
    var status: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("unit")
    var unit: String,

    @SerializedName("period")
    var period: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("values")
    var values: List<XYValues>,
)

data class XYValues(
    @SerializedName("x")
    var x: Float,

    @SerializedName("y")
    var y: Float,

    )


