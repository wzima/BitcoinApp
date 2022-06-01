package com.example.bitcoinapp.domain.repository

import com.example.bitcoinapp.domain.model.BitcoinPriceListing
import com.example.bitcoinapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface BitcoinPriceRepository {

    suspend fun getPriceListings(
        fetchFromRemote: Boolean
    ): Flow<Resource<List<BitcoinPriceListing>>>
}