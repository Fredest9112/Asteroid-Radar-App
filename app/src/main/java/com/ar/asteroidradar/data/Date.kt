package com.ar.asteroidradar.data

import com.ar.asteroidradar.data.Constants.API_QUERY_DATE_FORMAT
import com.ar.asteroidradar.data.Constants.DEFAULT_END_DATE_DAYS
import java.text.SimpleDateFormat
import java.util.*
import java.util.Date

object Date {

    private val calendar: Calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat(API_QUERY_DATE_FORMAT, Locale.getDefault())
    val currentTime: String = dateFormat.format(calendar.time)
    val sevenDaysAgo: String = dateFormat.format(getSevenDaysBefore())

    private fun getSevenDaysBefore(): Date {
        calendar.add(Calendar.DAY_OF_YEAR, -DEFAULT_END_DATE_DAYS)
        return calendar.time
    }
}