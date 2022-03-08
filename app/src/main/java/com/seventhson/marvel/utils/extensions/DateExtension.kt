package com.seventhson.marvel.utils.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val SERVER_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"
const val SHORT_SERVER_FORMAT = "yyyy-MM-dd"

const val STANDART_FORMAT = "MM/dd/yyyy"
const val STANDART_FORMAT_SHORT = "MM/dd/yy"

const val DAY_OF_WEEK_FORMAT = "EEE"
const val FULL_DAY_OF_WEEK_FORMAT = "EEEE"
const val MONTH_FORMAT = "MMM"
const val FULL_MONTH_FORMAT = "MMMM"
const val YEAR_FORMAT = "yyyy"
const val DAY_OF_MONTH_FORMAT = "dd"
const val HOUR_MINUTE_FORMAT = "HH:mm"

fun String.toDate(format: String): Date? {
    return try {
        SimpleDateFormat(format, Locale.getDefault()).parse(this)
    } catch (e: ParseException) {
        null
    }
}

fun Date?.toFormatString(format: String): String {
    return try {
        if (this != null)
            SimpleDateFormat(format, Locale.getDefault()).format(this)
        else ""
    } catch (e: ParseException) {
        ""
    }
}

fun String.toFormatStringDate(formatFrom: String, formatTo: String): String {
    return this.toDate(formatFrom).toFormatString(formatTo)
}

fun Long.toFormatDate(format: String): String {

    val formatter = SimpleDateFormat(format, Locale.getDefault())
    val calendar = Calendar.getInstance().apply {
        timeInMillis = this@toFormatDate
    }
    return formatter.format(calendar.time)
}