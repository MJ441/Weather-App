package com.example.weatherapp.screen.settingsScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.weatherapp.model.Unit
import com.example.weatherapp.widgets.WeatherAppBar


@Composable
fun SettingsScreen(navController: NavHostController, settingsViewModel: SettingsViewModel = hiltViewModel<SettingsViewModel>()) {
    

 var unitToggleState by remember { mutableStateOf(false) }

 val measurementUnit = listOf("Imperial (F)" , "Metric (C)")



 var choiceFromtDb = settingsViewModel.untList.collectAsState().value

 var choiseDef  by remember { mutableStateOf(0) }

 val defaultChoice = if (choiceFromtDb.isNullOrEmpty()) measurementUnit[0] else choiceFromtDb[0].unit

 var choiceState by remember { mutableStateOf(defaultChoice) }

 Scaffold (topBar = { WeatherAppBar(
  title = "Setting", icon = Icons.AutoMirrored.Filled.ArrowBack,
  isMainScreen = false ,
  navController = navController
 )

 {
  navController.popBackStack()

 }


 }


 ){ paddingValues ->

  Surface(modifier = Modifier
   .padding(paddingValues)
   .fillMaxWidth()
   .fillMaxHeight()) {

   Column(verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {

    Text(text = "Change Unit of Measurement", modifier = Modifier.padding(bottom = 15.dp)
    )
    IconToggleButton(checked = !unitToggleState, onCheckedChange = {
     unitToggleState = !it
     
     
     if (unitToggleState){
      choiceState = "Imperial (F)" 
      
     }else{
      
      choiceState = "Metric (C)"
     }

    }, modifier = Modifier
     .fillMaxWidth(0.6f)
     .clip(shape = RectangleShape)
     .padding(6.dp)
     .background(
      Color.Magenta.copy(alpha = 0.4f)
     )) {

Text(
 text = if (unitToggleState )"Fahrenheti F" else "Celsius C")


    }

    Button(
     onClick = {

      settingsViewModel.deleteAllUnit(Unit(unit = choiceState))
      settingsViewModel.insertUnit(Unit(unit = choiceState) )

     },
     modifier = Modifier
      .padding(3.dp)
      .align(Alignment.CenterHorizontally),
     shape = RoundedCornerShape(34.dp),
     colors = ButtonDefaults.buttonColors(Color(0xFFEFBE42))
    ) {


     Text(text = "SAVE", modifier = Modifier.padding(4.dp),
      color = Color.White,
      fontSize = 17.sp)


    }

   }

  }

 }
    
    
}





