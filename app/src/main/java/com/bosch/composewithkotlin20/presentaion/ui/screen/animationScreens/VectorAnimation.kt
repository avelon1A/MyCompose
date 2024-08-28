package com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens


import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.bosch.composewithkotlin20.R
import kotlinx.serialization.Serializable

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Preview(showBackground = true)
@Composable
fun VectorAnimationScreen() {
    val image = AnimatedImageVector.animatedVectorResource(R.drawable.animated)
    var atEnd by remember { mutableStateOf(false) }
    Image(
        painter = rememberAnimatedVectorPainter(image, atEnd),
        contentDescription = "Animated Vector",
        modifier = Modifier.fillMaxSize().
        clickable {
            atEnd = !atEnd
        },
        contentScale = ContentScale.Fit
    )
}

@Serializable
object VectorAnimationScreen

//@Preview(showBackground = true)
//@Composable
//fun PreviewVectorAnimationScreen() {
//    VectorAnimationScreen()
//}