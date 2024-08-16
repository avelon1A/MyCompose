package com.bosch.composewithkotlin20.presentaion.ui.screen.naviagtions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bosch.composewithkotlin20.R
import kotlinx.serialization.Serializable


@Composable
fun LottieAnimationScreen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.travel))
    var isPlaying by remember { mutableStateOf(true) }
    val progress by animateLottieCompositionAsState(composition, isPlaying = isPlaying)


    LaunchedEffect(key1 = progress) {
        if (progress == 0f) {
            isPlaying = true
        }
        if (progress == 1f) {
            isPlaying = false
        }

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(400.dp),
            progress = { progress })
        OutlinedButton(onClick = { isPlaying = true }) {
            Text("Play again")
        }

    }

}

@Serializable
object LottieAnimationScreen