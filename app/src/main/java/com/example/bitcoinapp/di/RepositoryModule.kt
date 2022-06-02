package com.example.bitcoinapp.di

import com.example.bitcoinapp.data.json.BitcoinPriceChartValuesParser
import com.example.bitcoinapp.data.json.BitcoinPriceListingsParser
import com.example.bitcoinapp.data.json.ChartJSONParser
import com.example.bitcoinapp.data.json.ListingsJSONParser
import com.example.bitcoinapp.data.repository.BitcoinChartValuesRepositoryImpl
import com.example.bitcoinapp.data.repository.BitcoinPriceRepositoryImpl
import com.example.bitcoinapp.domain.model.BitcoinChartValues
import com.example.bitcoinapp.domain.model.BitcoinPriceListing
import com.example.bitcoinapp.domain.repository.BitcoinChartValuesRepository
import com.example.bitcoinapp.domain.repository.BitcoinPriceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBitcoinPriceListingsParser(
        bitcoinPriceListing: BitcoinPriceListingsParser
    ): ListingsJSONParser<BitcoinPriceListing>

    @Binds
    @Singleton
    abstract fun bindBitcoinPriceRepository(
        bitcoinPriceRepositoryImpl: BitcoinPriceRepositoryImpl
    ): BitcoinPriceRepository


    @Binds
    @Singleton
    abstract fun bindBitcoinChartValuesParser(
        bitcoinPriceChartValuesParser: BitcoinPriceChartValuesParser
    ): ChartJSONParser<BitcoinChartValues>

    @Binds
    @Singleton
    abstract fun bindBitcoinChartValuesRepository(
        bitcoinChartValuesRepositoryImpl: BitcoinChartValuesRepositoryImpl
    ): BitcoinChartValuesRepository

}