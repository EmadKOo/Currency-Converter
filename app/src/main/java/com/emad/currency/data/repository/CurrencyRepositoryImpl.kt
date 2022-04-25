package com.emad.currency.data.repository

import com.emad.currency.data.local.CurrencyDao
import com.emad.currency.data.local.CurrencyEntity
import com.emad.currency.data.remote.CurrencyApiService
import com.emad.currency.domain.model.Currency
import java.lang.Exception
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val apiService: CurrencyApiService, private val currencyDao: CurrencyDao): CurrencyRepository {
    override suspend fun getCurrency(): List<Currency>{

            val cachedList : ArrayList<Currency> = currencyDao.getCurrencies().map { Currency(it.currencyName, it.currencySymbol, it.id) } as ArrayList<Currency>
            if (cachedList.size == 0){
                val list= apiService.getCurrencies()
                currencyDao.addCurrency(list.results.map {
                    CurrencyEntity(currencyName= it.value.currencyName, currencySymbol= it.value.currencySymbol, id= it.value.id)
                })
                cachedList.addAll(currencyDao.getCurrencies().map { Currency(it.currencyName, it.currencySymbol, it.id) })
            }
            return cachedList
    }

}