package com.emad.currency.domain.di

import com.emad.currency.utils.DateHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDateHelper(): DateHelper {
        return DateHelper()
    }
}