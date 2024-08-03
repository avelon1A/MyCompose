package com.bosch.composewithkotlin20.presentaion.ui.screen

import android.graphics.drawable.Icon
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.serialization.Serializable


@Composable
fun ScreenC(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        ExandCards("my name is aman","i am a android developer")
    }
}

@Composable
fun ExandCards(title:String, content:String){
    var expand by remember { mutableStateOf(false)}
    Card( modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)){
        Column(  modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = title)

            if (expand) {
                Text(text = content,Modifier.padding(top = 10.dp))
                IconButton(onClick = { expand = false }) {
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Collapse")
                }
            } else {
                IconButton(onClick = { expand = true }) {
                    Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Expand")
                }
            }
        }

    }
}

@Serializable
object ScreenC
