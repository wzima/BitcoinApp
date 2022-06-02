package com.example.bitcoinapp.util

class NumberFormatParser {
    companion object {
        fun getNumber(string: String) = string.replace(",", ".").toFloatOrNull() ?: 0.0f

    }
}