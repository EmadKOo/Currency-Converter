package com.emad.currency.data.remote.dto

data class Currency(
    val currencyName: String? = null,
    val currencySymbol: String? = null,
    val id: String? = null
)

data class CurrencyResponse(
    val results: HashMap<String, Currency>
)