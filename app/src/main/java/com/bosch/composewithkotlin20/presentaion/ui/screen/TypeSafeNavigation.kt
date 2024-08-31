package com.bosch.composewithkotlin20.presentaion.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bosch.composewithkotlin20.domain.navigation.AppNavigator
import kotlinx.serialization.Serializable

@Composable
fun TypeSafeNavigation(navController: AppNavigator) {
    var text by remember { mutableStateOf("type here") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        TextField(value = text,
            onValueChange = { newText -> text = newText })
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(onClick = { navController.navigateToTypeSafeNavigationSecondScreen(text) }) {
            Text(text = "click")

        }
    }

    }




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

@Serializable
object TypeSafeNavigation