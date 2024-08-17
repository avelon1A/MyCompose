package com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bosch.composewithkotlin20.R
import kotlinx.serialization.Serializable


@Composable
fun VectorAnimationScreen() {
    var isVisible by remember { mutableStateOf(true) }

    // Use a vector resource for the animation
    val icon: ImageVector = ImageVector.vectorResource(id = R.drawable.robot_android_svgrepo_com)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // AnimatedVisibility to animate the appearance of the icon
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
                exit = fadeOut(animationSpec = tween(durationMillis = 1000))
            ) {
                Icon(
                    painter = rememberVectorPainter(image = icon),
                    contentDescription = "Animated Icon",
                    modifier = Modifier.size(100.dp),
                    tint = Color.Blue
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Button to toggle visibility
            Button(onClick = { isVisible = !isVisible }) {
                Text(text = if (isVisible) "Hide Icon" else "Show Icon")
            }
        }
    }
}

@Serializable
object VectorAnimationScreen

@Preview(showBackground = true)
@Composable
fun PreviewVectorAnimationScreen() {
    VectorAnimationScreen()
}