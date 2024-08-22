package com.bosch.composewithkotlin20.presentaion.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.serialization.Serializable

@Composable
fun TypeSafeNavigation(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedButton(onClick = { navController.navigate(TypeSafeNavigationSecond("aman")) }) {
            Text(text = "click")

        }
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

