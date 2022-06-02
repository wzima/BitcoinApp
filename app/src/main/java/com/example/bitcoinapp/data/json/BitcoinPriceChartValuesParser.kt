package com.example.bitcoinapp.data.json

import android.util.Log
import com.example.bitcoinapp.data.remote.BitcoinPriceChartValuesJSONModel
import com.example.bitcoinapp.data.remote.CurrencyValuesJSONModel
import com.example.bitcoinapp.domain.model.BitcoinChartValues
import com.example.bitcoinapp.domain.model.BitcoinPriceListing
import com.github.mikephil.charting.data.Entry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BitcoinPriceChartValuesParser @Inject constructor() : ChartJSONParser<BitcoinChartValues> {
    override suspend fun parse(response: Response<BitcoinPriceChartValuesJSONModel>): BitcoinChartValues {
        return withContext(Dispatchers.IO) {
            if (response.isSuccessful) {

                val items = response.body()

                items?.values?.let { xyValues ->
                    val xy = xyValues.map { Entry(it.x, it.y) }.toList()
                    BitcoinChartValues(xy)

                } ?: BitcoinChartValues(arrayListOf())

            } else {
                Log.e("RETROFIT_ERROR", response.code().toString())
                BitcoinChartValues(arrayListOf())
            }


        }

    }
}