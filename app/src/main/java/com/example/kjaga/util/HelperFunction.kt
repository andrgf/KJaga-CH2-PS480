package com.example.kjaga.util

import android.util.Log
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun getCurrentDate(): String {
    val currentDate = LocalDate.now()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return currentDate.format(dateFormatter)
}

fun formatDateTime(isoDateString: String): String {
    val dateTime = ZonedDateTime.parse(isoDateString)
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return dateFormatter.format(dateTime)
}