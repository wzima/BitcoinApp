package com.example.bitcoinapp.di

import com.example.bitcoinapp.data.json.BitcoinPriceListingsParser
import com.example.bitcoinapp.data.json.JSONParser
import com.example.bitcoinapp.data.repository.BitcoinPriceRepositoryImpl
import com.example.bitcoinapp.domain.model.BitcoinPriceListing
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
    ): JSONParser<BitcoinPriceListing>

    @Binds
    @Singleton
    abstract fun bindBitcoinPriceRepository(
        bitcoinPriceRepositoryImpl: BitcoinPriceRepositoryImpl
    ): BitcoinPriceRepository

}