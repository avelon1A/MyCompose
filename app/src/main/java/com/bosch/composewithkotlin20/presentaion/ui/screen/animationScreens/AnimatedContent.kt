package com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.serialization.Serializable

@Composable

fun AnimatedContentScreen() {
    var count by remember { mutableStateOf(0) }
    var count1 by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AnimatedContent(targetState = count1, label = "title") { targetCount ->
                Text(text = "Count: $targetCount")
            }
            Button(onClick = { count1++ }, modifier = Modifier.padding(16.dp)) {
                Text("Add")
            }
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Row {
            Button(onClick = { count++ }, modifier = Modifier.padding(16.dp)) {
                Text("count++")
            }
            Row {
                AnimatedContent(
                    targetState = count, transitionSpec = {
                        if (targetState > initialState) {

                            (slideInVertically { height -> height } + fadeIn()).togetherWith(
                                slideOutVertically { height -> -height } + fadeOut())
                        } else {

                            (slideInVertically { height -> -height } + fadeIn()).togetherWith(
                                slideOutVertically { height -> height } + fadeOut())
                        }.using(
                            SizeTransform(clip = false)
                        )
                    }, label = ""
                ) { targetCount ->
                    Text(text = "$targetCount", fontSize = 30.sp)
                }
                Button(onClick = { count-- }, modifier = Modifier.padding(16.dp)) {
                    Text("Count--")
                }
            }


        }
    }
}

@Serializable
object AnimatedContentScreen

@Preview(showBackground = true)
@Composable
fun PreviewAnimatedContentScreen() {
    AnimatedContentScreen()
}