package com.example.bitcoinapp.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.bitcoinapp.domain.model.BitcoinChartValues
import com.example.bitcoinapp.domain.repository.BitcoinChartValuesRepository
import com.example.bitcoinapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

@HiltViewModel
class BitcoinChartValuesViewModel @Inject constructor(private val repository: BitcoinChartValuesRepository) :
    ViewModel() {

    private val _bitcoinChartValues = MutableLiveData<BitcoinChartValues>()
    val bitcoinChartValues: LiveData<BitcoinChartValues>
        get() = _bitcoinChartValues

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    init {
        getChartValues()
    }

    fun getChartValues() {
        viewModelScope.launch {
            repository
                .getBitcoinChartValues()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            Log.d("getChartValues", "Resource.Success")
                            result.data?.let { listings ->
                                _bitcoinChartValues.value = listings
                            }
                        }
                        is Resource.Error -> {
                            //show error message
                            Log.d("getChartValues", "Resource.Error")
                            Unit
                        }
                        is Resource.Loading -> {
                            //show loading
                            Log.d("getChartValues", "Resource.Loading")

                            _isLoading.value = result.isLoading
                            Unit
                        }
                    }
                }
        }
    }
}