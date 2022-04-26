package com.emad.currency.domain.di

import com.emad.currency.data.repository.CurrencyRepository
import com.emad.currency.domain.usecase.convert.ConvertCurrencyUseCase
import com.emad.currency.domain.usecase.convert.ConvertCurrencyUseCaseImpl
import com.emad.currency.domain.usecase.currecy.LoadCurrencyUseCase
import com.emad.currency.domain.usecase.currecy.LoadCurrencyUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCasesModule {
    @Singleton
    @Provides
    fun provideCurrencyUsecase(repository: CurrencyRepository): LoadCurrencyUseCase =
        LoadCurrencyUseCaseImpl(repository)


    @Singleton
    @Provides
    fun provideConvertingCurrencyUsecase(repository: CurrencyRepository): ConvertCurrencyUseCase =
        ConvertCurrencyUseCaseImpl(repository)

}