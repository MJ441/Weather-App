package com.example.weatherapp.reposistory

import com.example.weatherapp.data.WeatherDao
import com.example.weatherapp.model.Unit
import com.example.weatherapp.model.roomdatabass.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getFavorite(): Flow<List<Favorite>> = weatherDao.getFavorites()
    suspend fun insertFavorite(favorite: Favorite)  = weatherDao.insertFavorite(favorite)

    suspend fun updateFavorite(favorite: Favorite) = weatherDao.updateFavorite(favorite)
    suspend fun deleteAllFavorite(favorite: Favorite) = weatherDao.deleteAllFavorites()
    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorites(favorite)
    suspend fun getFavById(city:String) : Favorite = weatherDao.getFavById(city)


    fun getUnit(): Flow<List<Unit>> = weatherDao.getUnit()
    suspend fun insertUnit(unit: Unit) = weatherDao.insertUnit(unit)
    suspend fun updateUnit(unit: Unit) = weatherDao.updateUnit(unit)
    suspend fun deleteAllUnit(unit: Unit) = weatherDao.deleteAllUnitS()
    suspend fun deleteUnit(unit: Unit) = weatherDao.deleteUnit(unit)
}