package com.emad.currency.domain.di

import com.emad.currency.data.local.CurrencyDao
import com.emad.currency.data.remote.CurrencyApiService
import com.emad.currency.data.repository.CurrencyRepository
import com.emad.currency.data.repository.CurrencyRepositoryImpl
import com.emad.currency.utils.DateHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideCurrencyRepository(apiService: CurrencyApiService, currencyDao: CurrencyDao, dateHelper: DateHelper): CurrencyRepository = CurrencyRepositoryImpl(apiService, currencyDao, dateHelper)
}