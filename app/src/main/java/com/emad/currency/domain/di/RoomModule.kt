package com.emad.currency.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import android.app.Application
import androidx.room.Room
import com.emad.currency.data.local.CurrencyDB
import com.emad.currency.data.local.CurrencyDao
import com.emad.currency.utils.Constants.Companion.CURRENCY_DB



@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideRoomDB( app: Application): CurrencyDB {
        return Room.databaseBuilder(app, CurrencyDB::class.java, CURRENCY_DB)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providesCurrencyDao(db: CurrencyDB): CurrencyDao {
        return db.currencyDao()
    }
}