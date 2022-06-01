package com.example.bitcoinapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BitcoinPriceListingEntity::class],
    version = 2,
    exportSchema = false
)
abstract class BitcoinPriceDatabase : RoomDatabase() {

    abstract val dao: BitcoinPriceDao

    companion object {
        const val DB_NAME = "bitcoin_price"
    }

}