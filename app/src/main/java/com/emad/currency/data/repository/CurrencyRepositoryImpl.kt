package com.emad.currency.data.repository

import android.util.Log
import com.emad.currency.data.local.CurrencyDao
import com.emad.currency.data.local.CurrencyEntity
import com.emad.currency.data.remote.CurrencyApiService
import com.emad.currency.data.remote.dto.HistoricalResponse
import com.emad.currency.domain.model.Currency
import com.emad.currency.domain.model.HistoricalCurrency
import com.emad.currency.utils.DateHelper
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class CurrencyRepositoryImpl @Inject constructor(
    private val apiService: CurrencyApiService,
    private val currencyDao: CurrencyDao,
    private val dateHelper: DateHelper
) : CurrencyRepository {


    override suspend fun getCurrency(): List<Currency> {
        val cachedList: ArrayList<Currency> = currencyDao.getCurrencies()
            .map { Currency(it.currencyName, it.currencySymbol, it.id) } as ArrayList<Currency>
        if (cachedList.size == 0) {
            val list = apiService.getCurrencies()
            currencyDao.addCurrency(list.results.map {
                CurrencyEntity(
                    currencyName = it.value.currencyName,
                    currencySymbol = it.value.currencySymbol,
                    id = it.value.id
                )
            })
            cachedList.addAll(
                currencyDao.getCurrencies()
                    .map { Currency(it.currencyName, it.currencySymbol, it.id) })
        }
        return cachedList
    }

    override suspend fun convertCurrency(
        amount: Float,
        fromSymbol: String,
        toSymbol: String
    ): Float {
        val keyword = fromSymbol.uppercase() + "_" + toSymbol.uppercase()
        val response: Map<String, Float> = apiService.convertCurrency(keyword = keyword)
        return response.get(keyword)!! * amount
    }

    override suspend fun getHistoricalCurrency(): List<HistoricalCurrency> {
        return apiService.getHistoricalData(
            startDate = dateHelper.lastSevenDay(),
            endDate = dateHelper.currentFormattedDate()
        ).EGP_USD.map { HistoricalCurrency(it.key, it.value) }
    }
}