package com.emad.currency.utils

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateHelper @Inject constructor() {
    fun currentFormattedDate()= SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis())

    fun getFormattedDate(millis: Long)= SimpleDateFormat("yyyy-MM-dd").format(millis)

    fun lastSevenDay(): String{
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        return SimpleDateFormat("yyyy-MM-dd").format(calendar.timeInMillis)
    }
}