package com.ar.asteroidradar.utils

import com.ar.asteroidradar.utils.Constants.API_QUERY_DATE_FORMAT
import com.ar.asteroidradar.utils.Constants.DEFAULT_DELETE_DAYS
import com.ar.asteroidradar.utils.Constants.DEFAULT_END_DATE_DAYS
import java.text.SimpleDateFormat
import java.util.*
import java.util.Date

object Date {

    private val calendar: Calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat(API_QUERY_DATE_FORMAT, Locale.getDefault())
    val currentTime: String = dateFormat.format(calendar.time)
    val oneWeekAgo: String = dateFormat.format(getSevenDaysBefore())
    val twoWeeksAgo: String = dateFormat.format(getTwoWeeksAgo())

    private fun getSevenDaysBefore(): Date {
        calendar.add(Calendar.DAY_OF_YEAR, -DEFAULT_END_DATE_DAYS)
        return calendar.time
    }

    private fun getTwoWeeksAgo(): Date{
        calendar.add(DEFAULT_END_DATE_DAYS, -DEFAULT_DELETE_DAYS)
        return calendar.time
    }
}