package com.example.weatherapp.screen.aboutScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AboutScreen(navController: NavHostController) {
Column () {


    // App Title
    Text(
        text = "OpenWeather App",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(8.dp))

    // App Version
    Text(
        text = "Version: 1.0.0", // Update version accordingly
        style = MaterialTheme.typography.bodyLarge,
        color = Color.Gray
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Description
    Text(
        text = "This app provides accurate and up-to-date weather information using data from the OpenWeather API. You can check current weather, forecasts, humidity, wind speed, and more, based on your location or other cities.",
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center
    )
    
}}