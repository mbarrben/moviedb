package com.github.mbarrben.moviedb.model.rest

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateTypeAdapter(pattern: String) : JsonDeserializer<Date>, JsonSerializer<Date> {

  private val dateFormat = SimpleDateFormat(pattern, Locale.US)

  override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date? = try {
    dateFormat.parse(json?.asString)
  } catch (e: ParseException) {
    null
  }

  override fun serialize(src: Date?, typeOfSrc: Type?, context: JsonSerializationContext?) = JsonPrimitive(dateFormat.format(src))
}