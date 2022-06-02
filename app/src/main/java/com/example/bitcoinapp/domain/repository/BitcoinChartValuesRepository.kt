package com.example.bitcoinapp.domain.repository

import com.example.bitcoinapp.domain.model.BitcoinChartValues
import com.example.bitcoinapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface BitcoinChartValuesRepository {

    suspend fun getBitcoinChartValues(): Flow<Resource<BitcoinChartValues>>
}