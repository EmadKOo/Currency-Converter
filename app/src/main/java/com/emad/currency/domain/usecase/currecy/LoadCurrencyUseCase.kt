package com.emad.currency.domain.usecase.currecy

import com.emad.currency.data.remote.dto.Currency
import com.emad.currency.data.remote.dto.CurrencyResponse

interface LoadCurrencyUseCase {
    suspend operator fun invoke(): CurrencyResponse
}