package com.emad.currency.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [CurrencyEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CurrencyConverter::class)
abstract class CurrencyDB: RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}