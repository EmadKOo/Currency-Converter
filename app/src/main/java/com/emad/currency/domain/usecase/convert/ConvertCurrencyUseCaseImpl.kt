package com.emad.currency.domain.usecase.convert

import com.emad.currency.data.repository.CurrencyRepository
import javax.inject.Inject

class ConvertCurrencyUseCaseImpl @Inject constructor(private val repository: CurrencyRepository): ConvertCurrencyUseCase{
    override suspend fun invoke(amount: Float, fromSymbol: String, toSymbol: String): Float = repository.convertCurrency(amount, fromSymbol, toSymbol)
}