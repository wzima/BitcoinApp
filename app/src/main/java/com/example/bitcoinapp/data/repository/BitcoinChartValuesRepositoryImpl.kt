package com.example.bitcoinapp.data.repository

import com.example.bitcoinapp.data.json.ChartJSONParser
import com.example.bitcoinapp.data.remote.BlockchainChartAPI
import com.example.bitcoinapp.data.remote.BlockchainInfoAPI
import com.example.bitcoinapp.domain.model.BitcoinChartValues
import com.example.bitcoinapp.domain.repository.BitcoinChartValuesRepository
import com.example.bitcoinapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BitcoinChartValuesRepositoryImpl @Inject constructor(
    private val api: BlockchainChartAPI,
    private val bitcoinChartParser: ChartJSONParser<BitcoinChartValues>
) : BitcoinChartValuesRepository {


    override suspend fun getBitcoinChartValues(): Flow<Resource<BitcoinChartValues>> {
        return flow {
            emit(Resource.Loading(true))

            val remoteListings = try {
                val response = api.getBitcoinChartValues()
                bitcoinChartParser.parse(response)
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