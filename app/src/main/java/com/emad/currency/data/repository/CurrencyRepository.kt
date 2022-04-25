package com.emad.currency.data.repository

import com.emad.currency.data.remote.dto.Currency
import com.emad.currency.data.remote.dto.CurrencyResponse

interface CurrencyRepository {
    suspend fun getCurrency(): CurrencyResponse
}