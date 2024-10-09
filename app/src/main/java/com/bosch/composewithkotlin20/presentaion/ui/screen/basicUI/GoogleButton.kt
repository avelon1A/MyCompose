package com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bosch.composewithkotlin20.R
import kotlinx.serialization.Serializable

@Composable
fun GoogleButtonScreen() {
	
	Column(
		modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		GoogleButton()
		
	}
	
}

@Composable
fun GoogleButton() {
	var clicked by remember { mutableStateOf(false) }
	Surface(
		modifier = Modifier.height(48.dp),
		onClick = { clicked = !clicked },
		shape = Shapes().small,
		border = BorderStroke(width = 1.dp, color = Color.LightGray),
		color = MaterialTheme.colorScheme.surface,
	) {
		Row(
			modifier = Modifier
				.padding(start = 12.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
				.animateContentSize(animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.Center,
			
			) {
			Icon(
				painter = painterResource(id = R.drawable.google_icon),
				contentDescription = "googleIcon",
				tint = Color.Unspecified,
			)
			Text(text = "Sign in with Google")
			if (clicked) {
				Spacer(modifier = Modifier.width(16.dp))
				CircularProgressIndicator(
					modifier = Modifier
						.height(16.dp)
						.width(16.dp),
					strokeWidth = 2.dp,
					color = MaterialTheme.colorScheme.primary
				)
			}
		}
		
	}
}

@Serializable
object GoogleButtonScreen