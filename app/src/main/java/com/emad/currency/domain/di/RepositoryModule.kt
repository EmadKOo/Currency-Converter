package com.emad.currency.domain.di

import com.emad.currency.data.remote.CurrencyApiService
import com.emad.currency.data.repository.CurrencyRepository
import com.emad.currency.data.repository.CurrencyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideCurrencyRepository(apiService: CurrencyApiService): CurrencyRepository = CurrencyRepositoryImpl(apiService)
}