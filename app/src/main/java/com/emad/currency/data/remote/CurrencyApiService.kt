package com.emad.currency.data.remote

import com.emad.currency.BuildConfig
import com.emad.currency.data.remote.dto.Currency
import com.emad.currency.data.remote.dto.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {

    @GET("currencies")
    suspend fun getCurrencies(@Query("apiKey") apiKey: String = BuildConfig.API_KEY): CurrencyResponse
}