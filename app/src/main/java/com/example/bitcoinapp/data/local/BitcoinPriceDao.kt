package com.example.bitcoinapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BitcoinPriceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrice(
        companyListingEntity: BitcoinPriceListingEntity
    )

    @Query("DELETE FROM BitcoinPriceListingEntity")
    suspend fun clearCompanyListings()

    @Query("""SELECT * FROM BitcoinPriceListingEntity""")
    suspend fun getAll(): List<BitcoinPriceListingEntity>

}