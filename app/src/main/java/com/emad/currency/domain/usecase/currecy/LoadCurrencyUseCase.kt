package com.emad.currency.domain.usecase.currecy

import com.emad.currency.domain.model.Currency


interface LoadCurrencyUseCase {
    suspend operator fun invoke(): List<Currency>
}