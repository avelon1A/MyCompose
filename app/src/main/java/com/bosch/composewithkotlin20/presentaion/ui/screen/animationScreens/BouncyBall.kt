package com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable


@Composable
fun BouncingBallScreen() {
    val density = LocalDensity.current
    val position by remember { mutableFloatStateOf(200f) }
    val animatableObj = remember { androidx.compose.animation.core.Animatable(position) }
    var rectDeflection by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(key1 = true) {
        while (true) {
            animatableObj.animateTo(
                targetValue = with(density) { 610.dp.toPx() },
                animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
            )
            rectDeflection = 60f
            delay(100)
            rectDeflection = 0f
            animatableObj.animateTo(
                targetValue = 500f,
                animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val rectHeight = with(density) { 200.dp.toPx() }
            val radius = with(density) { 40.dp.toPx() }

            val rectPath = androidx.compose.ui.graphics.Path().apply {
                moveTo(0f, size.height - rectHeight)
                lineTo(size.width * 0.1f, size.height - rectHeight)
                quadraticBezierTo(
                    size.width * 0.5f, size.height - rectHeight + rectDeflection,
                    size.width * 0.9f, size.height - rectHeight
                )
                lineTo(size.width, size.height - rectHeight)
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            drawPath(
                path = rectPath,
                brush = Brush.linearGradient(listOf(Color(0XFF160C28), Color(0xffEFCB68)))
            )

            drawCircle(
                brush = Brush.linearGradient(listOf(Color(0XFFE1EFE6), Color(0XFFAEB7B3))),
                center = Offset(x = size.width / 2, y = animatableObj.value - radius),
                radius = radius
            )
        }
    }
}

@Serializable
object BouncingBallScreen


@Preview(showBackground = true)
@Composable
fun BouncingBallScreenPreview() {
    BouncingBallScreen()
}