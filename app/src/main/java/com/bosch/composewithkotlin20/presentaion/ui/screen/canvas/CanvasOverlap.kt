package com.bosch.composewithkotlin20.presentaion.ui.screen.canvas


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Composable
fun CanvasOverlap() {
    CompositingStratgey_ModulateAlpha()
}

@Preview(showBackground = true)
@Composable
fun CompositingStratgey_ModulateAlpha() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Canvas(
            modifier = Modifier.size(200.dp)
        ) {
            drawSquares()
        }

        Spacer(modifier = Modifier.size(36.dp))

        Canvas(modifier = Modifier
            .size(200.dp)
            .graphicsLayer {
                alpha = 0.5f
            }) {
            drawSquares()
        }
        Spacer(modifier = Modifier.size(36.dp))

        Canvas(modifier = Modifier
            .size(200.dp)
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.ModulateAlpha
                alpha = 0.75f
            }) {
            drawSquares()
        }
    }
}

private fun DrawScope.drawSquares() {

    val size = Size(100.dp.toPx(), 100.dp.toPx())
    drawRect(color = Red, size = size)
    drawRect(
        color = Purple, size = size,
        topLeft = Offset(size.width / 4f, size.height / 4f)
    )
    drawRect(
        color = Yellow, size = size,
        topLeft = Offset(size.width / 4f * 2f, size.height / 4f * 2f)
    )
}

val Purple = Color(0xFF7E57C2)
val Yellow = Color(0xFFFFCA28)
val Red = Color(0xFFEF5350)


@Serializable
object CanvasOverlap