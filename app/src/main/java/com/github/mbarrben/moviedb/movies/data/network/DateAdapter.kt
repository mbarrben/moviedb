package com.github.mbarrben.moviedb.movies.data.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class DateAdapter(pattern: String) {

    private val dateFormat = SimpleDateFormat(pattern, Locale.US)

    @ToJson
    fun toJson(date: Date): String = dateFormat.format(date)

    @FromJson
    fun fromJson(date: String): Date? = try {
        dateFormat.parse(date)
    } catch (e: ParseException) {
        null
    }
}