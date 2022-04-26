package com.emad.currency.data.remote

import com.emad.currency.BuildConfig
import com.emad.currency.data.remote.dto.Currency
import com.emad.currency.data.remote.dto.CurrencyResponse
import com.emad.currency.data.remote.dto.HistoricalResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {

    @GET("currencies")
    suspend fun getCurrencies(@Query("apiKey") apiKey: String = BuildConfig.API_KEY): CurrencyResponse

    @GET("convert")
    suspend fun convertCurrency(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("q") keyword: String,
        @Query("compact") compat: String= "ultra"
    ): Map<String, Float>

    @GET("convert")
    suspend fun getHistoricalData(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("q") keyword: String= "EGP_USD",
        @Query("compact") compat: String= "ultra",
        @Query("date") startDate: String,
        @Query("endDate") endDate: String,
    ): HistoricalResponse
}