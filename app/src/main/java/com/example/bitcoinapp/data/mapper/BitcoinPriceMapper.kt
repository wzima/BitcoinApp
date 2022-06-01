package com.example.bitcoinapp.data.mapper

import com.example.bitcoinapp.data.local.BitcoinPriceListingEntity
import com.example.bitcoinapp.domain.model.BitcoinPriceListing


fun BitcoinPriceListingEntity.toBitcoinPriceListing(): BitcoinPriceListing {
    return BitcoinPriceListing(
        currency = symbol,
        price = lastPrice,
    )
}

fun BitcoinPriceListing.toBitcoinPriceListingEntity(): BitcoinPriceListingEntity {
    return BitcoinPriceListingEntity(
        symbol = currency,
        lastPrice = price,
    )

}