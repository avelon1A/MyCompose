package com.bosch.composewithkotlin20.presentaion.ui.todo.common

import androidx.compose.foundation.Image
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter

@Composable
fun TodoTextField(
	value: String,
	onValueChange: (String) -> Unit,
	modifier: Modifier,
	placeholder: String,
	enabled: Boolean = true,
	singleLine: Boolean = false,
	maxLines: Int = 1,
	label: String

) {
	OutlinedTextField(
		value = value, onValueChange = onValueChange,
		label = { Text(label) },
		placeholder = { Text(placeholder) },
		modifier = modifier,
		enabled = enabled,
		singleLine = singleLine,
		maxLines = maxLines
	
	)
	
}

@Composable
fun ThemeImageView(
	url: String, modifier: Modifier
) {
	val painter: Painter = rememberAsyncImagePainter(url)
	
	Image(
		painter = painter, contentDescription = "Todo image",
		contentScale = ContentScale.FillBounds,
		modifier = modifier
	)
}

