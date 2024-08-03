package com.bosch.composewithkotlin20.presentaion.navigation

import com.bosch.composewithkotlin20.presentaion.ui.screen.HomeScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.OnBoardingScreen

fun navigation(destination: String): Any {
	return when (destination) {
		"onBoarding" -> OnBoardingScreen
		"home" -> HomeScreen
		else -> HomeScreen
	}
}
