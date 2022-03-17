package com.aprianto.mygithub.utils

import org.junit.Test

import org.junit.Assert.*

class HelperTest {

    private val dummyDate = "2022/03/17 12:00:00"
    private lateinit var helper: Helper

    @Test
    fun getSimpleDateFormat() {

        helper = Helper
        val simpleDate = helper.getSimpleDateFormat(dummyDate)

        // test salah
//        assertEquals(dummyDate, simpleDate, "17 Maret 2022 12.00")

        // test benar
        assertEquals(dummyDate, simpleDate, "17 Mar 2022 12.00")

    }
}