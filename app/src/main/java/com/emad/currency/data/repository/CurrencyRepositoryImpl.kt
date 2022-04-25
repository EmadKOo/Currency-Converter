package com.emad.currency.data.repository

import com.emad.currency.data.remote.CurrencyApiService
import com.emad.currency.data.remote.dto.Currency
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val apiService: CurrencyApiService): CurrencyRepository {
    override suspend fun getCurrency()= apiService.getCurrencies()

}