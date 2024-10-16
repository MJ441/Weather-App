package com.example.weatherapp.reposistory

import android.util.Log
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.Weather

import com.example.weatherapp.network.WeatherApi

import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi){

    suspend fun getWeather(cityQuery: String):DataOrException<Weather, Boolean,Exception>{

        val  response = try {

            api.getWeather(query = cityQuery)

        }catch (e:Exception){
            Log.d("REX", "getWeather: $e")
            return DataOrException(e = e)

        }

        Log.d("INSIDE", "getWeather: $response")
        return DataOrException(data = response)
    }



}