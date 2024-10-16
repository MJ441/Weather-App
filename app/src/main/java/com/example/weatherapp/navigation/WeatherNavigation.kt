package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.screen.main.MainScreen
import com.example.weatherapp.screen.WeatherSplashScreen
import com.example.weatherapp.screen.aboutScreen.AboutScreen
import com.example.weatherapp.screen.favoriteScreen.FavoriteScreen
import com.example.weatherapp.screen.main.MainViewModel
import com.example.weatherapp.screen.searchScreen.SearchScreen
import com.example.weatherapp.screen.settingsScreen.SettingsScreen

@Composable
fun WeatherNavigation (){

    val navController = rememberNavController()

   NavHost(navController = navController, startDestination = WeatherScreen.SplashScreen.name,  ) {

       composable(route = WeatherScreen.SplashScreen.name) {

           WeatherSplashScreen(navController = navController)

       }


       composable(
           route = "${WeatherScreen.MainScreen.name}/{city}",
           arguments = listOf(navArgument(name = "city") {


               type = NavType.StringType

           })
       ) { navBack ->

           navBack.arguments?.getString("city").let { city ->

               val mainViewModel = hiltViewModel<MainViewModel>()
               MainScreen(navController = navController, mainViewModel, city = city)

           }
       }


       composable(route = WeatherScreen.SearchScreen.name) {
           SearchScreen(navController = navController,)

       }

       composable(route = WeatherScreen.AboutScreen.name) {
           AboutScreen(navController = navController,)

       }

       composable(route = WeatherScreen.SettingsScreen.name) {
           SettingsScreen(navController = navController,)

       }

       composable(route = WeatherScreen.FavoriteScreen.name) {
           FavoriteScreen(navController = navController,)

       }


   }
}
