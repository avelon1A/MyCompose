package com.bosch.composewithkotlin20.presentaion.ui.screen.canvas


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable


@Composable
fun CanvasOval() {
    Box {
        Spacer(
            modifier = Modifier
                .padding(20.dp)
                .drawWithCache {
                    val path = Path()
                    path.moveTo(0f, size.height)
                    path.lineTo(size.width / 2f, size.height / 3f)
                    path.lineTo(size.width, size.height)
                    onDrawBehind {
                        drawPath(path, Color.Magenta, style = Stroke(width = 10f))
//                        drawLine(start = Offset(x = ))
                    }
                }
                .fillMaxSize()
        )


    }


}

@Preview(showBackground = true)
@Composable
fun PreviewDrawOval() {
    MaterialTheme {
        CanvasA()
    }
}


@Serializable
object CanvasOval