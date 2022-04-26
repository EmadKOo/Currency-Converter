package com.emad.currency.data.repository

import com.emad.currency.domain.model.Currency


interface CurrencyRepository {
    suspend fun getCurrency(): List<Currency>

    suspend fun convertCurrency(amount: Float, fromSymbol: String, toSymbol: String): Float
}