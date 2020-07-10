package com.zlk.akotlinlearn.common

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {

    private fun DateUtil() {}

    companion object {
        const val DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
        const val DEFAULT_FORMAT_DATE = "yyyy-MM-dd"
        const val DEFAULT_FORMAT_TIME = "HH:mm:ss"
        var defaultDateTimeFormat =
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        var defaultDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        var defaultTimeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        fun getDateTimeFromMillis(timeInMillis: Long): String? {
            return getDateTimeFormat(Date(timeInMillis))
        }

        fun getDateFromMillis(timeInMillis: Long): String? {
            return getDateFormat(Date(timeInMillis))
        }

        fun getDateTimeFormat(date: Date?): String? {
            return dateSimpleFormat(date, defaultDateTimeFormat)
        }

        fun getDateFormat(year: Int, month: Int, day: Int): String? {
            return getDateFormat(getDate(year, month, day))
        }

        fun getDateFormat(date: Date?): String? {
            return dateSimpleFormat(date, defaultDateFormat)
        }

        fun getTimeFormat(date: Date?): String? {
            return dateSimpleFormat(date, defaultTimeFormat)
        }

        fun dateFormat(sdate: String?, format: String?): String? {
            val formatter = SimpleDateFormat(format)
            val date = java.sql.Date.valueOf(sdate)
            return dateSimpleFormat(date, formatter)
        }

        fun dateFormat(date: Date?, format: String?): String? {
            val formatter = SimpleDateFormat(format)
            return dateSimpleFormat(date, formatter)
        }

        fun dateSimpleFormat(
            date: Date?,
            format: SimpleDateFormat?
        ): String? {
            var format = format
            if (format == null) {
                format = defaultDateTimeFormat
            }
            return if (date == null) "" else format.format(date)
        }

        fun getDateByDateTimeFormat(strDate: String): Date? {
            return getDateByFormat(strDate, defaultDateTimeFormat)
        }

        fun getDateByDateFormat(strDate: String): Date? {
            return getDateByFormat(strDate, defaultDateFormat)
        }

        fun getDateByFormat(strDate: String, format: String?): Date? {
            return getDateByFormat(strDate, SimpleDateFormat(format))
        }

        private fun getDateByFormat(
            strDate: String,
            format: SimpleDateFormat
        ): Date? {
            var format: SimpleDateFormat? = format
            if (format == null) {
                format = defaultDateTimeFormat
            }
            return try {
                format.parse(strDate)
            } catch (var3: ParseException) {
                var3.printStackTrace()
                null
            }
        }

        fun getDate(year: Int, month: Int, day: Int): Date? {
            val mCalendar = Calendar.getInstance()
            mCalendar[year, month - 1] = day
            return mCalendar.time
        }

        fun getIntervalDays(strat: String?, end: String?): Long {
            return (java.sql.Date.valueOf(end).time - java.sql.Date.valueOf(strat)
                .time) / 86400000L
        }

        fun getCurrentYear(): Int {
            val mCalendar = Calendar.getInstance()
            return mCalendar[1]
        }

        fun getCurrentMonth(): Int {
            val mCalendar = Calendar.getInstance()
            return mCalendar[2] + 1
        }

        fun getDayOfMonth(): Int {
            val mCalendar = Calendar.getInstance()
            return mCalendar[5]
        }

        fun getToday(): String? {
            val mCalendar = Calendar.getInstance()
            return getDateFormat(mCalendar.time)
        }

        fun getCurrentDateTime(): String? {
            val mCalendar = Calendar.getInstance()
            return getDateTimeFormat(mCalendar.time)
        }

        fun getYesterday(): String? {
            val mCalendar = Calendar.getInstance()
            mCalendar.add(5, -1)
            return getDateFormat(mCalendar.time)
        }

        fun getBeforeYesterday(): String? {
            val mCalendar = Calendar.getInstance()
            mCalendar.add(5, -2)
            return getDateFormat(mCalendar.time)
        }

        fun getOtherDay(diff: Int): String? {
            val mCalendar = Calendar.getInstance()
            mCalendar.add(5, diff)
            return getDateFormat(mCalendar.time)
        }

        fun getCalcDateFormat(sDate: String, amount: Int): String? {
            val date = getCalcDate(getDateByDateFormat(sDate), amount)
            return getDateFormat(date)
        }

        fun getCalcDate(date: Date?, amount: Int): Date {
            val cal = Calendar.getInstance()
            cal.time = date
            cal.add(5, amount)
            return cal.time
        }

        fun getDate(
            year: Int,
            month: Int,
            date: Int,
            hourOfDay: Int,
            minute: Int,
            second: Int
        ): Date? {
            val cal = Calendar.getInstance()
            cal[year, month, date, hourOfDay, minute] = second
            return cal.time
        }

        fun getYearMonthAndDayFrom(sDate: String): IntArray? {
            return getYearMonthAndDayFromDate(getDateByDateFormat(sDate))
        }

        fun getYearMonthAndDayFromDate(date: Date?): IntArray? {
            val calendar = Calendar.getInstance()
            calendar.time = date
            return intArrayOf(calendar[1], calendar[2], calendar[5])
        }
    }

}