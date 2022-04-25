package com.emad.currency.domain.usecase.currecy

import com.emad.currency.data.repository.CurrencyRepository
import javax.inject.Inject

class LoadCurrencyUseCaseImpl @Inject constructor(private val repository: CurrencyRepository): LoadCurrencyUseCase {
    override suspend fun invoke() = repository.getCurrency()
}