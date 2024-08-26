package com.bosch.composewithkotlin20.presentaion.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Composable
fun TypeSafeNavigation(navController: NavController) {
    var text by remember { mutableStateOf("type here") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        TextField(value = text,
            onValueChange = { newText -> text = newText })
    }

    OutlinedButton(onClick = { navController.navigate(TypeSafeNavigationSecond(text)) }) {
            Text(text = "click")

        }
    }


@Serializable
object TypeSafeNavigation

@Composable
fun TypeSafeNavigationSecond(navController: NavController, args: TypeSafeNavigationSecond) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = args.name, modifier = Modifier.clickable {
            navController.navigate(NewHome)
        })

    }

}

@Serializable
data class TypeSafeNavigationSecond(
    val name: String
)

