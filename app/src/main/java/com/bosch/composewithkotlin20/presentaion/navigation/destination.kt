package com.bosch.composewithkotlin20.presentaion.navigation

import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.HomeScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.NewHome
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.OnBoardingScreen
import com.bosch.composewithkotlin20.util.Const.UI_SCREEN

fun navigation(destination: String): Any {
	return when (destination) {
		"onBoarding" -> OnBoardingScreen
		"home" -> HomeScreen(UI_SCREEN)
		"NewHome" -> NewHome
		else -> NewHome
	}
}
