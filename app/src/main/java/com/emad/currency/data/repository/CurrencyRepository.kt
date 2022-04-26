package com.emad.currency.data.repository

import com.emad.currency.data.remote.dto.HistoricalResponse
import com.emad.currency.domain.model.Currency
import com.emad.currency.domain.model.HistoricalCurrency


interface CurrencyRepository {
    suspend fun getCurrency(): List<Currency>

    suspend fun convertCurrency(amount: Float, fromSymbol: String, toSymbol: String): Float

    suspend fun getHistoricalCurrency(): List<HistoricalCurrency>
}