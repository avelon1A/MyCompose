package com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Composable
fun Seekbar(){
	Column(modifier = Modifier.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center)
	{
			var sliderPosition by remember { mutableFloatStateOf(0f) }
			Column {
				Slider(
					value = sliderPosition,
					onValueChange = { sliderPosition = it },
							valueRange = 0f..100f
				)
				Text(text = sliderPosition.toString())
			}
		
		
	}
	
}

@Serializable
object Seekbar