package com.itpeople.sample.mvvm.utilities

import com.itpeople.sample.mvvm.utilities.DateUtility
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/* Created by Naeem on 22/01/2021.*/
object DateUtility {
    const val DATE_FORMAT_ZONE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DATE_FORMAT_VIMEO = "yyyy-MM-dd hh:mm:ss a"
    const val TIME_FORMAT = "hh:mm a"
    const val DATE_ONLY_FORMAT = "MMM dd, yyyy"
    val currentDateTime: String
        get() {
            val sdf =
                SimpleDateFormat(DATE_FORMAT_VIMEO)
            val now = Date()
            return sdf.format(now)
        }
    val currentDate: String
        get() {
            val sdf =
                SimpleDateFormat(DATE_ONLY_FORMAT)
            val now = Date()
            return sdf.format(now)
        }
    val currentTime: String
        get() {
            val sdf =
                SimpleDateFormat(TIME_FORMAT)
            val now = Date()
            return sdf.format(now)
        }

    fun changeFormat(oldFormat: String?, newFormat: String?, date: String?): String {
        val sdf = SimpleDateFormat(oldFormat)
        try {
            val d = sdf.parse(date)
            sdf.applyPattern(newFormat)
            return sdf.format(d)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }

    fun getDateFromString(currentDate: String?): Date? {
        var date: Date? = null
        try {
            date = SimpleDateFormat(DATE_ONLY_FORMAT).parse(currentDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }

    fun isDateInCurrentWeek(date: Date?): Boolean {
        val currentCalendar = Calendar.getInstance()
        currentCalendar[Calendar.DAY_OF_WEEK] = Calendar.SUNDAY
        val week = currentCalendar[Calendar.WEEK_OF_YEAR]
        val year = currentCalendar[Calendar.YEAR]
        val targetCalendar = Calendar.getInstance()
        targetCalendar[Calendar.DAY_OF_WEEK] = Calendar.SUNDAY
        targetCalendar.time = date
        val targetWeek = targetCalendar[Calendar.WEEK_OF_YEAR]
        val targetYear = targetCalendar[Calendar.YEAR]
        return week == targetWeek && year == targetYear
    }
}