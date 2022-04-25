package com.emad.currency.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

interface CurrencyConverter {
    @TypeConverter
    fun fromMoviesList(currency: List<CurrencyEntity?>?): String? {
        if (currency == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<CurrencyEntity?>?>() {}.type
        return gson.toJson(currency, type)
    }

    @TypeConverter
    fun toCurrenciesList(movie: String?): List<CurrencyEntity>? {
        if (movie == null)
            return null

        val gson = Gson()
        val type: Type = object : TypeToken<List<CurrencyEntity?>?>() {}.type
        return gson.fromJson<List<CurrencyEntity>>(movie, type)
    }
}