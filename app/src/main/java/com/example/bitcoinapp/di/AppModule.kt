package com.example.bitcoinapp.di

import android.content.Context
import androidx.room.Room
import com.example.bitcoinapp.data.remote.BitcoinPriceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideBitcoinPriceApi(): BitcoinPriceApi {
        return Retrofit.Builder()
            .baseUrl(BitcoinPriceApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

}