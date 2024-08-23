package com.bosch.composewithkotlin20.presentaion.ui.screen.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.serialization.Serializable


@Composable
fun CanvasLine() {

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawLine(
            color = Color.Red,
            start = Offset(0f, 0f),
            end = Offset(size.width, size.height),
            strokeWidth = 5f,
            cap = StrokeCap.Round
        )
        drawLine(
            color = Color.Green,
            start = Offset(size.width, 0f),
            end = Offset(0f, size.height),
            strokeWidth = 5f,
            cap = StrokeCap.Round
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDrawLineExample() {
    MaterialTheme {
        CanvasLine()
    }
}


@Serializable
object CanvasLine