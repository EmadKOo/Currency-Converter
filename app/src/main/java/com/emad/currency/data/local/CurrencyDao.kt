package com.emad.currency.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCurrency(movieEntitys: List<CurrencyEntity>): Array<Long>

    @Query("select * from currencytbl")
    suspend fun getCurrencies(): List<CurrencyEntity>

    @Query("DELETE from currencytbl")
    suspend fun clearDB()
}