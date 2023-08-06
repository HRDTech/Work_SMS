package com.solucioneshr.soft.work_sms.util

import java.text.SimpleDateFormat
import java.util.*

class DateConverters {

    fun fromTimestamp(value: Long?): String? {
        return value?.let {
            val myFormat = "dd/MM/yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            sdf.format(Date(it))
             }
    }


    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}