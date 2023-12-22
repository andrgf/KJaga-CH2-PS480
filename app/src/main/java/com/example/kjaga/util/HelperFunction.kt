package com.example.kjaga.util

import android.util.Log
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getCurrentDate(): String {
    val currentDate = LocalDate.now()
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return currentDate.format(dateFormatter)
}