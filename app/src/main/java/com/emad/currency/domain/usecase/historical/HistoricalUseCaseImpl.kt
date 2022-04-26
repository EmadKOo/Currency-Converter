package com.emad.currency.domain.usecase.historical

import com.emad.currency.data.remote.dto.HistoricalResponse
import com.emad.currency.data.repository.CurrencyRepository
import com.emad.currency.domain.model.HistoricalCurrency
import javax.inject.Inject

class HistoricalUseCaseImpl @Inject constructor(private val repository: CurrencyRepository): HistoricalUseCase {
    override suspend fun invoke() = repository.getHistoricalCurrency()
}