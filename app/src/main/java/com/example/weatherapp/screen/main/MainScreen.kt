package com.example.weatherapp.screen.main

import android.util.Log

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherObject
import com.example.weatherapp.navigation.WeatherScreen
import com.example.weatherapp.utils.formatDate
import com.example.weatherapp.utils.formatDecimals
import com.example.weatherapp.widgets.HumidityWindPressureRow
import com.example.weatherapp.widgets.SunsetSunriseRow
import com.example.weatherapp.widgets.WeatherAppBar
import com.example.weatherapp.widgets.WeatherStateImage

@Composable
fun  MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String?
){




    val weatherData = produceState<DataOrException<Weather,Boolean,Exception>>(
        initialValue = DataOrException(loading = true) ) {



        value = mainViewModel.getWeatherData(city = city.toString())


    }.value


    if (weatherData.loading == true){
        CircularProgressIndicator()

    }else if (weatherData.data != null) {

        MainScaffold(weather = weatherData.data!!,navController =navController)

        //Text(text = " Main Screen ${weatherData.data!!.main}")
    }


}

@Composable
fun MainScaffold(weather: Weather, navController: NavController) {

    Scaffold(topBar = { WeatherAppBar(title = weather.name + "  ,${weather.sys.country}",
        icon = Icons.AutoMirrored.Filled.ArrowBack,
        navController = navController,
        onAddActionClick = {

            navController.navigate(WeatherScreen.SearchScreen.name)
        } ,
        elevation = 5.dp){

Log.d("TAG", "MainScaffold : Button Click")
    }})



    { paddingValues ->


        MainContent(data = weather, padding = paddingValues)


    }


}





@Composable
fun MainContent(data: Weather, padding: PaddingValues) {


    val imageUrl = "https://openweathermap.org/img/wn/${data.weather[0].icon}.png"

    Column(modifier = Modifier
        .padding(padding)
        .padding(4.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {


        Text(text = formatDate( data.dt) ,

          color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(6.dp)
        )
        Surface(modifier = Modifier
            .padding(4.dp)
            .size(200.dp),
            shape = CircleShape, color = Color(0xFFFFC400)
        ) {

            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                WeatherStateImage(imageUrl = imageUrl)

                Text(text = formatDecimals( data.main.temp) + "°", style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold)
                Text(text =data.weather[0].description, fontStyle = FontStyle.Italic )


            }

        }

        HumidityWindPressureRow(weather = data)

        HorizontalDivider()
        
        SunsetSunriseRow(weather = data)
        Text(text = "weather Details")
        
        Surface() {
          LazyColumn(modifier = Modifier.padding(2.dp),
              contentPadding = padding) {
              
              
              items( items =  data.weather){ item: WeatherObject ->
                  
                  Text(text = item.toString())
                  
              }
          }  
        }
    }

}

