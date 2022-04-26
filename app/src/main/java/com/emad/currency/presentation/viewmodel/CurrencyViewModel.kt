package com.emad.currency.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emad.currency.domain.model.Currency
import com.emad.currency.domain.usecase.convert.ConvertCurrencyUseCase
import com.emad.currency.domain.usecase.currecy.LoadCurrencyUseCase
import com.emad.currency.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val loadCurrencyUseCase: LoadCurrencyUseCase,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : ViewModel() {
    private val _loadCurrencyStateFlow = MutableLiveData<Resource<List<Currency>>>(Resource.Init())
    val loadCurrencyStateFlow: LiveData<Resource<List<Currency>>> = _loadCurrencyStateFlow

    private val _convertCurrencyLiveData= MutableLiveData<Resource<Float>>(Resource.Init())
    val convertCurrencyLiveData= _convertCurrencyLiveData

    val fromCurrencyLiveData= SingleLiveEvent<Currency>()
    val toCurrencyLiveData= SingleLiveEvent<Currency>()
    val amountLiveData= MutableLiveData<Float>(1f)

    init {
        loadCurrency()
    }

    fun loadCurrency() = viewModelScope.launch {
        try {
            _loadCurrencyStateFlow.postValue(Resource.Loading())
            _loadCurrencyStateFlow.postValue(Resource.Success(loadCurrencyUseCase.invoke()))
        } catch (ex: Exception) {
            _loadCurrencyStateFlow.postValue(Resource.Error(ex.localizedMessage))
        }
    }

    fun setFromCurrency(currency: Currency){
        fromCurrencyLiveData.postValue(currency)
    }

    fun setToCurrency(currency: Currency){
        toCurrencyLiveData.postValue(currency)
    }

    fun setAmount(amount: Float){
        amountLiveData.postValue(amount)
    }

    fun convertCurrency()= viewModelScope.launch {
        try {
            _convertCurrencyLiveData.postValue(Resource.Loading())
            _convertCurrencyLiveData.postValue(Resource.Success(convertCurrencyUseCase.invoke(amountLiveData.value?:1f, fromSymbol = fromCurrencyLiveData.value?.id?:"", toCurrencyLiveData.value?.id?:"")))
        }catch (ex: Exception){
            _convertCurrencyLiveData.postValue(Resource.Error(ex.localizedMessage))
        }
    }
}

