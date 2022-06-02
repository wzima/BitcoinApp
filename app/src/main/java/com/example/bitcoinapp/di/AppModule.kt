package com.example.bitcoinapp.di

import com.example.bitcoinapp.data.remote.BlockchainChartAPI
import com.example.bitcoinapp.data.remote.BlockchainInfoAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBitcoinPriceApi(): BlockchainInfoAPI {
        return Retrofit.Builder()
            .baseUrl(BlockchainInfoAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun provideBitcoinChartApi(): BlockchainChartAPI {
        return Retrofit.Builder()
            .baseUrl(BlockchainChartAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

}