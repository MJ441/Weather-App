package com.example.weatherapp.data

class DataOrException<T, Boolean, E: Exception>(


    val data: T? = null,
    var loading: Boolean? =null,
    var e: E? = null


)
