package com.bosch.composewithkotlin20.presentaion.ui.screen.canvas

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Composable
fun rememberGyroscopeSensor(context: Context): Float {
    var rotation by remember { mutableStateOf(0f) }

    DisposableEffect(context) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)

        val listener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    val rotationMatrix = FloatArray(9)
                    SensorManager.getRotationMatrixFromVector(rotationMatrix, it.values)
                    val orientationValues = FloatArray(3)
                    SensorManager.getOrientation(rotationMatrix, orientationValues)
                    rotation = orientationValues[2] // Z-axis rotation
                }
            }
        }

        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    return rotation
}


@Composable
fun CustomWaterBottle(modifier: Modifier = Modifier, rotation: Float = 0f) {
    Canvas(modifier = modifier) {
        val bottleWidth = size.width * 0.6f
        val bottleHeight = size.height * 0.8f
        val neckWidth = bottleWidth * 0.5f
        val neckHeight = size.height * 0.1f
        val capHeight = size.height * 0.05f
        val waterHeight = bottleHeight * 0.7f

        val bottleColor = Color(0xFFB0C4DE) // Light steel blue for bottle
        val capColor = Color(0xFF1C1C1C) // Dark gray for cap
        val waterColor = Color(0xFF00BFFF) // Deep sky blue for water

        // Draw the bottle body
        drawRoundRect(
            color = bottleColor,
            topLeft = Offset(
                (size.width - bottleWidth) / 2,
                size.height - bottleHeight
            ),
            size = Size(bottleWidth, bottleHeight),
            cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx())
        )

        // Define the RoundRect for clipping
        val roundRect = RoundRect(
            Rect(
                (size.width - bottleWidth) / 2,
                size.height - bottleHeight,
                (size.width + bottleWidth) / 2,
                size.height
            ),
            CornerRadius(16.dp.toPx(), 16.dp.toPx())
        )

        // Clip the canvas to the bottle shape
        val clipPath = Path().apply {
            addRoundRect(roundRect, Path.Direction.CounterClockwise)
        }
        clipPath(clipPath) {
            // Calculate the offset for the water based on the rotation
            val waterOffsetX =
                (size.width - bottleWidth) / 2 - rotation * 5f // Adjust 5f for sensitivity
            val waterOffsetY = size.height - waterHeight

            // Draw the water inside the bottle
            drawRoundRect(
                color = waterColor,
                topLeft = Offset(
                    waterOffsetX,
                    waterOffsetY
                ),
                size = Size(bottleWidth, waterHeight),
                cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx())
            )
        }

        // Draw the bottle neck
        drawRoundRect(
            color = bottleColor,
            topLeft = Offset(
                (size.width - neckWidth) / 2,
                size.height - bottleHeight - neckHeight
            ),
            size = Size(neckWidth, neckHeight),
            cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx())
        )

        // Draw the bottle cap
        drawRoundRect(
            color = capColor,
            topLeft = Offset(
                (size.width - neckWidth) / 2,
                size.height - bottleHeight - neckHeight - capHeight
            ),
            size = Size(neckWidth, capHeight),
            cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx())
        )

        // Draw the highlight on the bottle
        drawRoundRect(
            color = Color.White.copy(alpha = 0.2f),
            topLeft = Offset(
                (size.width - bottleWidth) / 2 + bottleWidth * 0.1f,
                size.height - bottleHeight + bottleHeight * 0.1f
            ),
            size = Size(bottleWidth * 0.15f, bottleHeight * 0.8f),
            cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx())
        )
    }
}

@Composable
fun WaterBottleScreen(context: Context) {
    val rotation = rememberGyroscopeSensor(context)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        CustomWaterBottle(
            modifier = Modifier
                .size(150.dp, 400.dp)
                .background(Color.White),
            rotation = rotation
        )
    }
}

@Serializable
object WaterBottleScreen