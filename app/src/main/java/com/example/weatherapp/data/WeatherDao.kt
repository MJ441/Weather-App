package com.example.weatherapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weatherapp.model.Unit
import com.example.weatherapp.model.roomdatabass.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query(value = "SELECT * from fav_tbl")
    fun getFavorites (): Flow<List<Favorite>>

    @Query(value = "SELECT * from fav_tbl where city = :city")
    suspend fun getFavById(city : String) : Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite (favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite)

    @Query(value = "DELETE from fav_tbl")
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavorites(favorite: Favorite)

    // Unit table


    @Query(value = "SELECT * from SETTING_TBL")
    fun getUnit (): Flow<List<Unit>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit (unit: Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(favorite: Unit)

    @Query(value = "DELETE from SETTING_TBL")
    suspend fun deleteAllUnitS()


    @Delete
    suspend fun deleteUnit(unit : Unit)
}