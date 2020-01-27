package com.mohamedmenasy.core.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * Converts unix timestamp to formatted date and time
 * @receiver unix timestamp
 * @return the formatted date and time
 */
fun Long.toFullDate(): String {

  val sdf = SimpleDateFormat("yy-MM-dd, HH:mm:ss", Locale.US)
  val date = java.util.Date(this * 1000)
  return sdf.format(date)
}

/**
 * Converts unix timestamp to formatted date
 * @receiver unix timestamp
 * @return the formatted date
 */
fun Long.toDate(): String {

  val sdf = SimpleDateFormat("yy-MM-dd", Locale.US)
  val date = java.util.Date(this * 1000)
  return sdf.format(date)
}

/**
 * Converts unix timestamp to formatted time
 * @receiver unix timestamp
 * @return the formatted time
 */
fun Long.toTime(): String {

  val sdf = SimpleDateFormat("HH:mm:ss", Locale.US)
  val date = java.util.Date(this * 1000)
  return sdf.format(date)
}
