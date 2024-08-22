package com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.bosch.composewithkotlin20.R
import kotlinx.serialization.Serializable


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentScreenIcons() {
    Column(modifier = Modifier.fillMaxSize()) {
        var expanded by remember { mutableStateOf(false) }

        Surface(
            color = MaterialTheme.colorScheme.primary, modifier = Modifier.clickable {
                expanded = !expanded
            }
        ) {

            AnimatedContent(
                targetState = expanded,
                transitionSpec = {
                    fadeIn(animationSpec = tween(150, 150)) with
                            fadeOut(animationSpec = tween(150)) using
                            SizeTransform { initialSize, targetSize ->
                                if (targetState) {
                                    keyframes {
                                        IntSize(targetSize.width, initialSize.height) at 150
                                        durationMillis = 300
                                    }
                                } else {
                                    keyframes {
                                        IntSize(initialSize.width, targetSize.height) at 150
                                        durationMillis = 300
                                    }
                                }
                            }
                }, label = ""
            ) { targetExpanded ->
                if (targetExpanded) {
                    TextContent()
                } else {
                    IconContent()
                }
            }

        }

    }
}

@Composable
private fun IconContent() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(MaterialTheme.colorScheme.onPrimaryContainer)
    ) {
        Image(
            painter = painterResource(id = R.drawable.robot_android_svgrepo_com),
            contentDescription = "appIcon"
        )
    }
}

@Composable
private fun TextContent() {
    Box(
        modifier = Modifier
            .size(500.dp)
            .background(MaterialTheme.colorScheme.onPrimaryContainer)
    ) {
        Text(text = stringResource(R.string.AnimatedTextContent))
    }
}

@Serializable
object AnimatedContentScreenIcons