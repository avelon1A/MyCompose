package com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable


@Preview
@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun SlideBox() {
    val infinityTransition = rememberInfiniteTransition("infinity Transition")
    val process = infinityTransition.animateFloat(
        label = "offset",
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    val offset = 100.dp

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset(y=-offset * process.value,x=offset * process.value)
                .background(Color.Red)
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset(y=offset * process.value,x=-offset * process.value)
                .background(Color.Magenta)
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset(y=-offset * process.value,x=-offset * process.value)
                .background(Color.Yellow)
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset(y=offset * process.value,x=offset * process.value)
                .background(Color.Green)
        )


    }
}
@Serializable
object SlideBox