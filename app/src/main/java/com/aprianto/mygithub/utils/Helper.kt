package com.aprianto.mygithub.utils

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

object Helper {

    private var dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())

    fun toast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun getCurrentDate(): String {

        val date = Date()
        return dateFormat.format(date)
    }

    fun getSimpleDateFormat(dateValue: String): String {
        val date = dateFormat.parse(dateValue)
        val simpleDate = SimpleDateFormat("dd MMM yyyy HH.mm", Locale.getDefault())
        return simpleDate.format(date)
    }

}