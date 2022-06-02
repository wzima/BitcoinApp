package com.example.bitcoinapp.util

import java.text.SimpleDateFormat
import java.util.*

class TimeConverter {
    companion object {
        fun fromMillis(millis: Long): String {
            val timeD = Date(millis * 1000)
            val sdf = SimpleDateFormat("M/YY")

            return sdf.format(timeD)

        }
    }
}