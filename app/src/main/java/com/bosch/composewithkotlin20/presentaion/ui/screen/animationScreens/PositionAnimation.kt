package com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import com.bosch.composewithkotlin20.R
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Composable
fun ImageMover() {
    var offset by remember { mutableStateOf(IntOffset(0, 0)) }
    val animatableX = remember { Animatable(0f) }
    val animatableY = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize().pointerInput(Unit) {
            detectTapGestures { tapOffset ->
                scope.launch {
                    // Animate both X and Y coordinates to the touch location
                    animatableX.animateTo(tapOffset.x)
                    animatableY.animateTo(tapOffset.y)
                }
            }
        }
    ) {
        offset = IntOffset(animatableX.value.toInt(), animatableY.value.toInt())
        Image(
            painter = painterResource(id = R.drawable.person),
            contentDescription = null,
            modifier = Modifier.offset { offset }
        )
    }
}
@Serializable
object ImageMover