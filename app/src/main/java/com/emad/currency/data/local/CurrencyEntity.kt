package com.emad.currency.data.local

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.emad.currency.utils.Constants
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
@Entity(tableName = Constants.CURRENCY_ENTITY)
data class CurrencyEntity (
    val currencyName: String?= null,
    val currencySymbol: String?= null,
    val id: String?= null,

): Parcelable{
    @PrimaryKey(autoGenerate = true)
    var currencyID: Int =0
}