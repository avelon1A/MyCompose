package com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.serialization.Serializable


@Composable
fun ThirdScreen(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Green),
        contentAlignment = Alignment.Center
        ) {
        Box(modifier = Modifier
            .background(Color.Black)
            ,contentAlignment = Alignment.Center){
            Box (modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .background(Color.Blue))
            Text(text = "Third Screen", color = Color.White, fontSize = 40.sp)
        }
        
    }

}
@Serializable
object ThirdScreen