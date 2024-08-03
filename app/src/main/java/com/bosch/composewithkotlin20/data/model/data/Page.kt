package com.bosch.composewithkotlin20.data.model.data

import androidx.annotation.DrawableRes
import com.bosch.composewithkotlin20.R

data class Page(
	val title:String,
	val content:String,
	@DrawableRes val image:Int
)
val pages = listOf(
	Page(
		"Introduction to Jetpack Compose",
		"Learn about Jetpack Compose, the modern toolkit for building native Android UI. Understand its advantages over the traditional XML-based approach.",
		R.drawable.firstjt
	),
	Page(
		"Building Layouts in Compose",
		"Discover how to build complex layouts using Jetpack Compose's Composables. Learn about different layout components like Column, Row, Box, and more.",
		R.drawable.sec
	),
	Page(
		"State Management in Compose",
		"Understand state management in Jetpack Compose. Explore how to handle state using Compose's state APIs and see how state flows through a composable hierarchy.",
		R.drawable.thirdjt
	),
	)