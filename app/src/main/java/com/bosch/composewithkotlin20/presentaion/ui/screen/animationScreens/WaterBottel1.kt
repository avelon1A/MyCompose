package com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable



@Preview(showBackground = true)
@Composable
fun WaterBottelCanvas() {
    val totalWater = remember {
        2400
    }
    var usedWater by remember {
     mutableIntStateOf(200)
    }

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        WaterBottle1(totalWater = totalWater, unit = "", usedWater = usedWater)
        Spacer(modifier  = Modifier.height(20.dp))
      OutlinedButton(onClick = { usedWater += 200}) {
       Text(text = "Drink Water")
      }


    }

}

@Composable
fun WaterBottle1(
    modifier: Modifier = Modifier,
    totalWater: Int,
    unit: String,
    usedWater: Int,
    waterColor: Color = Color(0xFF1A6FFD),
    bottleColor: Color = Color.White,
    bottleCapColor: Color = Color(0xFF132379),
) {


    val waterPercentage by animateFloatAsState(
        targetValue = usedWater.toFloat() / totalWater.toFloat(),
        label = "for water percentage",
        animationSpec = tween(durationMillis = 1000)

    )
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(600.dp)
    )
    {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val capWidth = size.width * 0.55f
            val capHeight = size.height * 0.11f

            val bottleBodyPath = Path().apply {
                moveTo(x = width * 0.3f, y = height * 0.1f)
                lineTo(x = width * 0.3f, y = height * 0.2f)
                quadraticTo(
                    x1 = 0f,
                    y1 = height * 0.3f,
                    x2 = 0f,
                    y2 = height * 0.4f
                )
                lineTo(x = 0f, y = height * 0.95f)
                quadraticTo(
                    x1 = 0f,
                    y1 = height,
                    x2 = width * 0.05f,
                    y2 = height
                )
                lineTo(x = width * 0.95f, y = height)
                quadraticTo(
                    x1 = width,
                    y1 = height,
                    x2 = width,
                    y2 = height * 0.95f
                )
                lineTo(x = width, y = height * 0.4f)
                quadraticTo(
                    x1 = width,
                    y1 = height * 0.3f,
                    x2 = width * 0.7f,
                    y2 = height * 0.2f
                )
                lineTo(x = width * 0.7f, y = height * 0.1f)
                close()
            }

            clipPath(path = bottleBodyPath) {
                drawRect(
                    color = bottleColor,
                    size = size

                )

                val waterPosition = (1 - waterPercentage) * size.height
                val bottleCapPath = Path().apply {
                    moveTo(x = 0f, y = waterPosition)
                    lineTo(x = size.width, y = waterPosition)
                    lineTo(x = size.width, y = size.height)
                    lineTo(x = 0f, y = size.height)
                    close()
                }
                clipPath(path = bottleCapPath) {
                    drawRect(
                        color = waterColor,
                        size = size
                    )



                }


            }
            drawRoundRect(
                color = bottleCapColor,
                size = Size(capWidth, capHeight),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(45f,45f),
                topLeft = Offset(x = size.width/2 - capWidth/2f, y = 0f),

                )
        }


    }
}

@Serializable
object WaterBottelCanvas