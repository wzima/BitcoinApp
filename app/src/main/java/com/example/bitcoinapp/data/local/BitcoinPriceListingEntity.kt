package com.example.bitcoinapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class BitcoinPriceListingEntity(
    val lastPrice: Float,
    val symbol: String,
    @PrimaryKey val id: Int? = null
)