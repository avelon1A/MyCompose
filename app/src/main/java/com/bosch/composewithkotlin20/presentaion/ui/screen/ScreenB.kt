package com.bosch.composewithkotlin20.presentaion.ui.screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Composable
fun ScreenB(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TitileSection()
        GridButton(modifier = Modifier.weight(1f))
        BottomBar()

    }
}
@Composable
fun BottomBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Give money", color = Color.Green, fontSize = 18.sp)
        Text(text = "View GitHub", color = Color.Black, fontSize = 18.sp)
    }
}

@Composable
fun GridButton(modifier: Modifier) {
    Column(modifier = modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Row(modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonSection("1")
            ButtonSection("2")
        }
        Row(modifier = Modifier.weight(1f)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            ButtonSection("3")
            ButtonSection("4")
        }
        Row(modifier = Modifier.weight(1f)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            ButtonSection("5")
            ButtonSection("6")
        }


    }
}

@Composable
fun ButtonSection(s: String) {
    ElevatedButton(onClick = { }) {
        Text("Elevated")
    }

}

@Composable
fun TitileSection() {
    Column(modifier = Modifier
        .fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "why not Compose!",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        Text(
            text = "version 1.0",
            color = Color.Gray,
            fontSize = 16.sp,
        )


    }
}

@Serializable
object ScreenB
