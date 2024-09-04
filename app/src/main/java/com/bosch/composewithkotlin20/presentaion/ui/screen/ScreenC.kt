package com.bosch.composewithkotlin20.presentaion.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.serialization.Serializable


@Composable
fun ScreenC(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        ExandCards("Tap to expand", "This is Expanded Card")
    }
}

@Composable
@Preview
fun ScreenCPreview() {
    ExandCards(title = "Tap to expand", content = "This is Expanded Card")
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
            .background(MaterialTheme.colorScheme.surface)) {


            if (expand) {
                Text(text = content,Modifier.padding(top = 10.dp))
                IconButton(onClick = { expand = false },modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Collapse",modifier = Modifier.fillMaxWidth())
                }
            } else {
                Text(text = title)
                IconButton(onClick = { expand = true },modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Expand",modifier = Modifier.fillMaxWidth())
                }
            }
        }

    }
}

@Serializable
object ScreenC
