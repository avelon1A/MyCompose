package com.bosch.composewithkotlin20.presentaion.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.bosch.composewithkotlin20.presentaion.ui.screen.HomeButton
import com.bosch.composewithkotlin20.presentaion.ui.screen.MainScreenButtons

object Buttons {

    @Composable
    fun AppScreenIcons(
        buttonList: List<MainScreenButtons>,
        navController: NavController,
        innerPadding: PaddingValues,
        modifier: Modifier,
        chunks: Int = 2,
    ) {
        HomeButton(
            buttonList = buttonList,
            navController = navController,
            innerPadding =innerPadding,
            modifier = modifier,
            chunks  = chunks
        )
    }

}
