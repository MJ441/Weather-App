package com.example.weatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.model.Unit
import com.example.weatherapp.model.roomdatabass.Favorite

@Database(entities = [Favorite::class, Unit::class], version = 2 , exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun WeatherDao(): WeatherDao
}