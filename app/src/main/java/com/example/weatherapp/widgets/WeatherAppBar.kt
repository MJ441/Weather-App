package com.example.weatherapp.widgets





import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info

import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.R
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.roomdatabass.Favorite
import com.example.weatherapp.navigation.WeatherScreen
import com.example.weatherapp.screen.favoriteScreen.FavoriteViewModel
import com.example.weatherapp.utils.formatDateTime


@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onAddActionClick: () -> Unit = {},
    onButtonClick: () -> Unit = {}) {


    val showDialog = remember {
        mutableStateOf(false)
    }


    val expanded = remember { mutableStateOf(false) }




    Card(

        modifier = Modifier
            .padding(top = 42.dp)
            .height(80.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.Transparent),
        elevation = CardDefaults.cardElevation(elevation),


        ) {


        Row(modifier = Modifier.padding(top = 25.dp,)) {


            Row(modifier = Modifier.padding(bottom = 23.dp)) {


                if (icon != null) {

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = icon,//Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.clickable {

                                onButtonClick.invoke()
                            }
                        )

                    }
                    if (isMainScreen){
                        Icon(imageVector = Icons.Default.Favorite, contentDescription ="Favorite Icon",
                            modifier = Modifier.scale(0.9f).clickable {

                                favoriteViewModel.insertFavorite(Favorite(
                                    city = title.split(",")[0],
                                    country = title.split(",")[0],



                                ))

                            },
                            tint = Color.Red.copy(alpha = 0.6f))
                    }
                }

            }

            Row(modifier = Modifier.padding(start = 110.dp)) {


                Text(
                    text = title,

                    color = Color.Black,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                )

            }

            Row(modifier = Modifier.padding(start = 68.dp, bottom = 26.dp)) {


                if (isMainScreen) {
                    IconButton(onClick = { onAddActionClick.invoke() }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")

                    }
                    IconButton(onClick = { showDialog.value = true
                    expanded.value = true
                    }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "MoreVert",


                            ) }
                } else Box() {


                }

                if (showDialog.value) {


                    ShowSettingDropMenu(
                        showDialog = showDialog,
                        expanded = expanded,
                        navController = navController
                    )


                }


            }
            ////

        }
    }






}
@Composable
fun ShowSettingDropMenu(
    showDialog: MutableState<Boolean>,
    navController: NavController,
    expanded: MutableState<Boolean>,

    ) {



    val items = listOf("About", "Favorites", "Settings")

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.TopEnd)
        .absolutePadding(top = 45.dp, right = 25.dp)) {

        DropdownMenu(expanded = expanded.value , onDismissRequest = { expanded.value =false  },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)
        ) {







            items.forEachIndexed { index, text ->


                DropdownMenuItem(text = {

                    Icon(
                        imageVector = when (text) {

                            "About" -> Icons.Default.Info

                            "Favorites" -> Icons.Default.Favorite

                            else -> Icons.Default.Settings


                        } , contentDescription = null,
                        tint = Color.LightGray,
                    )
                    Text(text = text, modifier = Modifier
                        .clickable {

                            navController.navigate(

                                when (text) {

                                    "About" -> WeatherScreen.AboutScreen.name

                                    "Favorites" -> WeatherScreen.FavoriteScreen.name

                                    else -> WeatherScreen.SettingsScreen.name


                                }
                            )

                        }
                        .padding(start = 40.dp),
                        fontWeight = FontWeight.W300
                    )



                }, onClick = {  expanded.value = false
                    showDialog.value = false

                })



                }

        }

    }




}


@Composable
fun SunsetSunriseRow(weather: Weather) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 15.dp, bottom = 6.dp), horizontalArrangement = Arrangement.SpaceBetween) {

        Row() {

            Image(painter = painterResource(id = R.drawable.oip), contentDescription = "Sunrise",
                modifier = Modifier.size(32.dp))
            Text(text = formatDateTime(weather.sys.sunrise),
                style = MaterialTheme.typography.headlineSmall)

        }


        Row() {

            Image(painter = painterResource(id = R.drawable.sunrise__horizon__sea__sun__sunrise__sunset__weather_icon_512),
                contentDescription = "Sunset",
                modifier = Modifier.size(32.dp))
            Text(text = formatDateTime(weather.sys.sunset),
                style = MaterialTheme.typography.headlineSmall)

        }
    }


}

@Composable
fun WeatherStateImage(imageUrl: String) {


    Image(painter = rememberAsyncImagePainter( imageUrl ), contentDescription ="icon image" ,
        modifier = Modifier.size(80.dp))


}

@Composable
fun HumidityWindPressureRow(weather: Weather) {

    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {

        Row() {

            Icon(painter = painterResource(id = R.drawable.humidity_icon_14), contentDescription ="humidity",
                modifier = Modifier.size(20.dp))
            Text(text = "${weather.main.humidity}h%", style = MaterialTheme.typography.titleLarge)


        }

        Row() {

            Icon(painter = painterResource(id = R.drawable.pressure_icon_png_5), contentDescription ="pressure",
                modifier = Modifier.size(20.dp))
            Text(text = "${weather.main.pressure}psi", style = MaterialTheme.typography.titleLarge)



        }

        Row() {

            Icon(painter = painterResource(id = R.drawable.wind_png_transparent_image), contentDescription ="wind",
                modifier = Modifier.size(20.dp))
            Text(text = "${weather.wind.speed}mph ", style = MaterialTheme.typography.titleLarge)



        }



    }




}


