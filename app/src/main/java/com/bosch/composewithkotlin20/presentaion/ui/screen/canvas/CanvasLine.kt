package com.bosch.composewithkotlin20.presentaion.ui.screen.canvas

import android.graphics.Paint
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.serialization.Serializable


@Composable
fun CanvasLine() {

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawLine(
            color = Color.Red,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width / 2, size.height / 2),
            strokeWidth = 5f,
            cap = StrokeCap.Round
        )
        drawLine(
            color = Color.Blue,
            start = Offset(size.width / 2, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = 5f,
            cap = StrokeCap.Round
        )
        drawLine(
            color = Color.Yellow,
            start = Offset(size.width / 2, size.height / 2),
            end = Offset(size.width / 2, 0f),
            strokeWidth = 5f,
            cap = StrokeCap.Round
        )
        drawLine(
            color = Color.Green,
            start = Offset(size.width, 0f),
            end = Offset(0f, size.height),
            strokeWidth = 5f,
            cap = StrokeCap.Round
        )
        drawLine(
            color = Color.Magenta,
            start = Offset(size.width /2, size.height /2 ),
            end = Offset(size.width /2, size.height),
            strokeWidth = 5f,
            cap = StrokeCap.Round
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDrawLineExample() {
    MaterialTheme {
        CanvasLine()
    }
}

@Preview(showBackground = true)
@Composable
fun BasicCanvas() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(Color.Blue, size = Size(100f, 100f))
    }
}

@Preview(showBackground = true)
@Composable
fun ShapesCanvas() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(Color.Red, size = Size(200f, 100f))
        drawCircle(Color.Green, radius = 50f, center = Offset(100f, 100f))
        drawLine(Color.Black, start = Offset(0f, 0f), end = Offset(200f, 200f), strokeWidth = 5f)
    }
}

@Preview(showBackground = true)
@Composable
fun TextCanvas() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawContext.canvas.nativeCanvas.drawText(
            "Hello Canvas!",
            100f, 100f,
            Paint().apply {
                color = android.graphics.Color.MAGENTA
                textSize = 40f
                textAlign = Paint.Align.CENTER
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PathCanvas() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(100f, 100f)
            lineTo(200f, 100f)
            lineTo(150f, 200f)
            close()
        }
        drawPath(path, Color.Yellow, style = Stroke(width = 4f))
    }
}

//@Preview(showBackground = true)
//@Composable
//fun EffectsCanvas() {
//    Canvas(modifier = Modifier.fillMaxSize()) {
//        drawIntoCanvas { canvas ->
//            canvas.saveLayer(null,
//                androidx.compose.ui.graphics.Paint().apply {
//                color = Color.Red
//                alpha = 128F
//            })
//            drawRect(Color.Red, size = Size(200f, 200f))
//            canvas.restore()
//        }
//    }
//}
@Preview(showBackground = true)
@Composable
fun AnimatedCanvas() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animatedValue by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
            Color.Blue,
            radius = animatedValue,
            center = Offset(size.width / 2, size.height / 2)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BezierPathCanvas() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(100f, 100f)
            cubicTo(150f, 50f, 250f, 150f, 300f, 100f)
            lineTo(300f, 400f)
            lineTo(100f, 400f)
            close()
        }
        drawPath(path, color = Color.Magenta, style = Stroke(width = 7f))
    }
}

@Preview(showBackground = true)
@Composable
fun StarPathCanvas() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(200f, 50f)
            lineTo(220f, 150f)
            lineTo(300f, 150f)
            lineTo(240f, 200f)
            lineTo(260f, 300f)
            lineTo(200f, 240f)
            lineTo(140f, 300f)
            lineTo(160f, 200f)
            lineTo(100f, 150f)
            lineTo(180f, 150f)
            close()
        }
        drawPath(path, color = Color.Red, style = Stroke(width = 4f))
    }
}

@Preview(showBackground = true)
@Composable
fun GeometricPatternCanvas() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(50f, 50f)
            lineTo(200f, 50f)
            lineTo(200f, 200f)
            lineTo(50f, 200f)
            close()

            moveTo(100f, 100f)
            lineTo(150f, 100f)
            lineTo(150f, 150f)
            lineTo(100f, 150f)
            close()

            moveTo(250f, 250f)
            cubicTo(300f, 200f, 350f, 300f, 400f, 250f)
            lineTo(400f, 400f)
            lineTo(250f, 400f)
            close()
        }
        drawPath(path, color = Color.Blue, style = Stroke(width = 4f))
    }
}


@Preview(showBackground = true)
@Composable
fun ArcPathCanvas() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(100f, 100f)
            arcTo(
                Rect(50f, 50f, 150f, 150f),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 180f,
                forceMoveTo = false
            )
            lineTo(200f, 100f)
            close()
        }
        drawPath(path, color = Color.Green, style = Stroke(width = 4f))
    }
}

@Preview
@Composable
fun CombinedPathCanvas() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path1 = Path().apply {
            moveTo(100f, 100f)
            lineTo(200f, 100f)
            lineTo(150f, 200f)
            close()
        }

        val path2 = Path().apply {
            moveTo(200f, 200f)
            cubicTo(250f, 150f, 300f, 250f, 350f, 200f)
            close()
        }

        drawPath(path1, color = Color.Blue, style = Stroke(width = 4f))
        drawPath(path2, color = Color.Red, style = Stroke(width = 4f))
    }
}

@Preview
@Composable
fun DashedPathCanvas() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(50f, 50f)
            lineTo(250f, 50f)
            lineTo(250f, 250f)
            lineTo(50f, 250f)
            close()
        }
        drawPath(
            path = path,
            color = Color.Cyan,
            style = Stroke(
                width = 4f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f), 0f)
            )
        )
    }
}





@Serializable
object CanvasLine