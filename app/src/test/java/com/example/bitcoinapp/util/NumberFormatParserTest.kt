package com.example.bitcoinapp.util

import org.junit.Assert.*
import org.junit.Test

class NumberFormatParserTest {

    @Test
    fun `invalid input returns 0`() {
        val input = "abc"
        val parsedNumber = NumberFormatParser.getNumber(input)
        assertEquals(parsedNumber, 0f)
    }

    @Test
    fun `input with comma returns valid number`() {
        val input = "2,3"
        val parsedNumber = NumberFormatParser.getNumber(input)
        assertEquals(parsedNumber, 2.3f)
    }

    @Test
    fun `input with dot returns valid number`() {
        val input = "2.3"
        val parsedNumber = NumberFormatParser.getNumber(input)
        assertEquals(parsedNumber, 2.3f)
    }

}