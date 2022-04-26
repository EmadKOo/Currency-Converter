package com.emad.currency.data.remote

import com.emad.currency.BuildConfig
import com.emad.currency.data.remote.dto.Currency
import com.emad.currency.data.remote.dto.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {

    @GET("currencies")
    suspend fun getCurrencies(@Query("apiKey") apiKey: String = BuildConfig.API_KEY): CurrencyResponse
//https://free.currconv.com/api/v7/convert?apiKey=ba2a57bfff878d5ea52c&compact=ultra&q=MXN_STD
    @GET("convert")
    suspend fun convertCurrency(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("q") keyword: String,
        @Query("compact") compat: String= "ultra"
    ): Map<String, Float>
}