package com.example.randomuserapp.common

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.parseDate(): String{
    val odt = OffsetDateTime.parse(this)
    val dtf = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH)

    return  dtf.format(odt)
}