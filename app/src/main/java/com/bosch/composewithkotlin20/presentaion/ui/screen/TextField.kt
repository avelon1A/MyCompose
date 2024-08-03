package com.bosch.composewithkotlin20.presentaion.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import kotlinx.serialization.*

@Composable
fun Textfield() {
	Column(
		modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		var text by remember { mutableStateOf("type here") }
		TextField(value = text,
			onValueChange = { newText -> text = newText },
			label = { Text("label") })
		
		/**   icon can also be add in this file
		 * there can be two types of icon
		 * 1st leading and 2nd trailing*/
		
		OutlinedTextField(value = text,
			onValueChange = { newText -> text = newText },
			label = { Text("label") },
			)
		
	}
}

@Serializable
object Textfield