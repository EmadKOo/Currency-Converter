package com.emad.currency.domain.usecase.historical

import com.emad.currency.domain.model.HistoricalCurrency

interface HistoricalUseCase {
    suspend operator fun invoke(): List<HistoricalCurrency>
}