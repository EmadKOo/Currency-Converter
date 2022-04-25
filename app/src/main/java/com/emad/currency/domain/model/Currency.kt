package com.emad.currency.domain.model

data class Currency(
    val currencyName: String? = null,
    val currencySymbol: String? = null,
    val id: String? = null
){
    override fun toString(): String {
        return this.currencyName.toString()
    }
}