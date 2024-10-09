package com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.serialization.Serializable

@Composable
fun TextScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Hello", fontSize = 40.sp)
        Text(
            text = "Android",
            modifier = Modifier
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp), fontSize = 40.sp
        )
        Text(
            text = "Italic",
            modifier = Modifier
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp), fontSize = 40.sp,
            fontStyle = FontStyle.Italic

        )
        Text(
            text = "Bold",
            modifier = Modifier
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp), fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Alignment",
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp), fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Left
        )
    }


}


@Serializable
object TextScreen

@Preview
@Composable
fun SimpleComposablePreview() {
    TextScreen()
}