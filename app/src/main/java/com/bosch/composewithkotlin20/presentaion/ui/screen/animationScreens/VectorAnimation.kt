package com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens


import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bosch.composewithkotlin20.R
import kotlinx.serialization.Serializable

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Preview(showBackground = true)
@Composable
fun VectorAnimationScreen() {
    val image = AnimatedImageVector.animatedVectorResource(R.drawable.animated)
    var atEnd by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = rememberAnimatedVectorPainter(image, atEnd),
            contentDescription = "Animated Vector",
            modifier = Modifier.fillMaxSize().clickable {
                atEnd = !atEnd
            },
            contentScale = ContentScale.Fit
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter).padding(bottom = 150.dp),
            text = "Click to show",
            fontSize = 33.sp
        )
    }
}


@Serializable
object VectorAnimationScreen

//@Preview(showBackground = true)
//@Composable
//fun PreviewVectorAnimationScreen() {
//    VectorAnimationScreen()
//}