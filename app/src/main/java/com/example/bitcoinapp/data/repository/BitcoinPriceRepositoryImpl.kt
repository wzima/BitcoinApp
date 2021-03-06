package com.example.bitcoinapp.data.repository

import com.example.bitcoinapp.data.json.ListingsJSONParser
import com.example.bitcoinapp.data.remote.BlockchainInfoAPI
import com.example.bitcoinapp.domain.model.BitcoinPriceListing
import com.example.bitcoinapp.domain.repository.BitcoinPriceRepository
import com.example.bitcoinapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BitcoinPriceRepositoryImpl @Inject constructor(
    private val api: BlockchainInfoAPI,
    private val listingsJSONParser: ListingsJSONParser<BitcoinPriceListing>
) : BitcoinPriceRepository {


    override suspend fun getPriceListings(): Flow<Resource<List<BitcoinPriceListing>>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteListings = try {
                val response = api.getCurrencyListings()
                listingsJSONParser.parseCurrencyValues(response)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Loading(false))
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Loading(false))
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let { listings ->
                emit(Resource.Success(data = listings))
                emit(Resource.Loading(false))
            }
        }
    }
}