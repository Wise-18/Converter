package com.example.converter

import android.app.Application
import androidx.lifecycle.*
import com.example.converter.model.ConversionResponse
import com.example.converter.model.CurrencyListResponse
import com.example.converter.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyConverterViewModel(application: Application) : AndroidViewModel(application) {
    private val _currencies = MutableLiveData<List<String>>()
    val currencies: LiveData<List<String>> = _currencies

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        loadCurrencies()
    }

    fun loadCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.getCurrencyList()
                if (response.isSuccessful && response.body()?.success == true) {
                    _currencies.postValue(response.body()!!.currencies.keys.toList())
                } else {
                    _error.postValue("Ошибка загрузки валют: ${response.code()}")
                }
            } catch (e: Exception) {
                _error.postValue("Сетевая ошибка: ${e.message}")
            }
        }
    }

    fun convert(from: String, to: String, amount: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.convertCurrency(from = from, to = to, amount = amount)
                if (response.isSuccessful && response.body()?.success == true) {
                    _result.postValue(response.body()!!.result.toString())
                } else {
                    _error.postValue("Ошибка конвертации: ${response.body()?.error}")
                }
            } catch (e: Exception) {
                _error.postValue("Ошибка: ${e.message}")
            }
        }
    }
}