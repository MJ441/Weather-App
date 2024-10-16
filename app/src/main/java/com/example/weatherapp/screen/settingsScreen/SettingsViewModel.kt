package com.example.weatherapp.screen.settingsScreen


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.reposistory.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.weatherapp.model.Unit

@HiltViewModel
class SettingsViewModel @Inject constructor(private val repository: WeatherDbRepository):
        ViewModel() {


        private val _untList = MutableStateFlow<List<Unit>>(emptyList())

        val untList = _untList.asStateFlow()

        init {
            viewModelScope.launch (Dispatchers.IO){

                repository.getUnit().distinctUntilChanged()
                    .collect{
                            listofUnt ->
                        if (listofUnt.isNullOrEmpty()){
                            Log.d("TAG", "EMty")
                        }else{

                            _untList.value = listofUnt

                            Log.d("FAV", "${untList.value}")
                        }

                    }




            }
        }

        fun insertUnit(unit: com.example.weatherapp.model.Unit )
                = viewModelScope.launch { repository.insertUnit(unit = unit) }

        fun updateUnit(unit: Unit) = viewModelScope.launch { repository.updateUnit(unit) }

        fun deleteAllUnit(unit: Unit) = viewModelScope.launch { repository.deleteAllUnit( unit) }
    }
