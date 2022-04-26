package com.emad.currency.domain.usecase.convert

interface ConvertCurrencyUseCase {
    suspend operator fun invoke(
        amount: Float,
        fromSymbol: String,
        toSymbol: String
    ): Float
}