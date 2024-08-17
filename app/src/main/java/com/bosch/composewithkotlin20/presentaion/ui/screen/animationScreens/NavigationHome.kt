package com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens

import AnimatedVisibilityExample
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.bosch.composewithkotlin20.R
import com.bosch.composewithkotlin20.presentaion.ui.common.AppBar
import com.bosch.composewithkotlin20.presentaion.ui.screen.ButtonInfo
import com.bosch.composewithkotlin20.presentaion.ui.screen.CustomButton
import kotlinx.serialization.Serializable


@Composable
fun NavigationHome(navController: NavController) {
    val buttonList = listOf(
        ButtonInfo("lottie ", LottieAnimationScreen),
        ButtonInfo("Animated Visibility", AnimatedVisibilityExample),
        ButtonInfo("Animate enter and exit for children", AnimatedChildren),
        ButtonInfo("Animated ContentScreen", AnimatedContentScreen),
        ButtonInfo("vector animation", VectorAnimationScreen)
    )
    Scaffold(
        topBar = {
            AppBar(R.drawable.arrow_back, navController)
        },
        content = { innerPadding ->
            NavigationScreen(navController, innerPadding, buttonList)
        }

    )
}

@Composable
fun NavigationScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    list: List<ButtonInfo>
) {
    LazyColumn(modifier = Modifier.padding(innerPadding)) {
        items(list) { buttonItem ->
            CustomButton(
                modifier = Modifier,
                text = buttonItem.title,
                onClick = { navController.navigate(buttonItem.route) })
        }
    }
}


@Serializable
object NavigationHome