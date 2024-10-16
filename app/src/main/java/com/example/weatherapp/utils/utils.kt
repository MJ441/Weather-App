package com.example.weatherapp.utils


import android.content.ClipData.Item
import java.text.SimpleDateFormat



fun formatDate(timestamp: Int): String {

    val sdf = SimpleDateFormat("EEE, MMM d")
    val date = java.util.Date(timestamp.toLong() * 1000)


    return sdf.format(date)
}

fun formatDateTime(timestamp: Int) : String {


  val  sdf = SimpleDateFormat("hh:mm:aa")
    val data = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(data)

}

fun formatDecimals (item: Double) : String {

    val item = item - 273.15

    return "%.0f".format(item)
}