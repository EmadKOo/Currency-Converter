package com.emad.currency.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emad.currency.domain.model.Currency
import com.emad.currency.domain.usecase.currecy.LoadCurrencyUseCase
import com.emad.currency.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(private val loadCurrencyUseCase: LoadCurrencyUseCase,): ViewModel() {
    private val _loadCurrencyStateFlow = MutableLiveData<Resource<List<Currency>>>(Resource.Init())
    val loadCurrencyStateFlow: LiveData<Resource<List<Currency>>> = _loadCurrencyStateFlow

    init {
        loadCurrency()
    }

    fun loadCurrency()= viewModelScope.launch {
        try {
            _loadCurrencyStateFlow.postValue(Resource.Loading())
            _loadCurrencyStateFlow.postValue(Resource.Success(loadCurrencyUseCase.invoke()))
        }catch (ex: Exception){
            _loadCurrencyStateFlow.postValue(Resource.Error(ex.localizedMessage))
            Log.d(TAG, "loadCurrency: " + ex.cause)
            Log.d(TAG, "loadCurrency: " + ex.message)
            Log.d(TAG, "loadCurrency: " + ex.stackTrace)
            Log.d(TAG, "loadCurrency: " + ex.suppressed)
        }
    }
}

private const val TAG = "CurrencyViewModel"