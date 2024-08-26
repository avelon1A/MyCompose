package com.bosch.composewithkotlin20.presentaion.ui.screen.canvas


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable


@Composable
fun CanvasOval() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            scale(scaleX = 10f, scaleY = 10f) {
                drawCircle(Color.Blue, radius = 17.dp.toPx())
            }
        }
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