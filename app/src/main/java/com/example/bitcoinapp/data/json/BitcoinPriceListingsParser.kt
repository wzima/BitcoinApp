package com.example.bitcoinapp.data.json

import android.util.Log
import com.example.bitcoinapp.data.remote.CurrencyValuesJSONModel
import com.example.bitcoinapp.domain.model.BitcoinPriceListing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BitcoinPriceListingsParser @Inject constructor() : JSONParser<BitcoinPriceListing> {
    override suspend fun parse(response: Response<Map<String, CurrencyValuesJSONModel>>): List<BitcoinPriceListing> {
        return withContext(Dispatchers.IO) {
            if (response.isSuccessful) {

                val items = response.body()

                items?.mapNotNull { item ->
                    BitcoinPriceListing(item.key, item.value.last ?: return@mapNotNull null)
                } ?: listOf()
            } else {

                Log.e("RETROFIT_ERROR", response.code().toString())
                listOf()
            }


            //            csvReader
//                .readAll()
//                .drop(1)
//                .mapNotNull { line ->
//                    val symbol = line.getOrNull(0)
//                    val name = line.getOrNull(1)
//                    val exchange = line.getOrNull(2)
//                    CompanyListing(
//                        name = name ?: return@mapNotNull null,
//                        symbol = symbol ?: return@mapNotNull null,
//                        exchange = exchange ?: return@mapNotNull null,
//                    )
//                }
//                .also {
//                    csvReader.close()
//                }
        }

    }
}