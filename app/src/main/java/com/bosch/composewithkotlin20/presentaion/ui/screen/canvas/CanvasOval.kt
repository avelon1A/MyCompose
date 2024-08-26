package com.bosch.composewithkotlin20.presentaion.ui.screen.canvas


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Composable
fun CanvasOval() {

    var scale by remember { mutableFloatStateOf(1f) }

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animatedScale = infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        scale = animatedScale.value
                    }
                )
            }
            .scale(scale)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            val radius = 24.dp.toPx()
            val gradientBrush = Brush.radialGradient(
                colors = listOf(Color.Yellow, Color.Red),
                center = Offset(size.width / 2, size.height / 2),
                radius = radius
            )

            scale(scaleX = 10f, scaleY = 10f) {
                drawCircle(gradientBrush, radius = 17.dp.toPx())
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Eyes()
            ArcInSpacerExample()
        }
    }
}


@Composable
fun ArcInSpacerExample() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            val arcSize = Size(width = 100.dp.toPx(), height = 100.dp.toPx())

            val canvasWidth = size.width
            val canvasHeight = size.height

            val topLeftOffset = Offset(
                x = (canvasWidth - arcSize.width) / 2f,
                y = (canvasHeight - arcSize.height) / 6f
            )

            drawArc(
                color = Color.Red,
                startAngle = 20f,
                sweepAngle = 140f,
                useCenter = false,
                size = arcSize,
                topLeft = topLeftOffset,
                style = Stroke(width = 4.dp.toPx())
            )
        }
    }
}


@Composable
private fun Eyes() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.SpaceAround
    ) {

        Canvas(
            modifier = Modifier
                .padding(10.dp)
        ) {

            scale(scaleX = 10f, scaleY = 10f) {
                drawCircle(Color.Red, radius = 5.dp.toPx())
            }
        }

        Canvas(
            modifier = Modifier
                .padding(10.dp)
        ) {

            scale(scaleX = 10f, scaleY = 10f) {
                drawCircle(Color.Red, radius = 5.dp.toPx())
            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDrawOval() {
    MaterialTheme {
        CanvasOval()
    }
}


@Serializable
object CanvasOval