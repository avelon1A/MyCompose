import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.bosch.composewithkotlin20.R
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun FishCanvas() {
    var animationStarted by remember { mutableStateOf(false) }

    val screenWidth = 1000f
    val fishWidth = 200.dp.toPx()
    val targetPosition = 1000f + fishWidth
    val amplitude = 200f
    val frequency = 1f
    val rotationMax = 10f

    val infiniteTransitionFish = rememberInfiniteTransition(label = "")

    val fishOffsetX by infiniteTransitionFish.animateFloat(
        initialValue = -fishWidth,
        targetValue = targetPosition,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 9000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "Fish Offset X"
    )

    val infiniteTransition = rememberInfiniteTransition()
    val fishOffsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = amplitude,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val fishRotation by infiniteTransition.animateFloat(
        initialValue = -rotationMax,
        targetValue = rotationMax,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 200, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    LaunchedEffect(Unit) {
        delay(1000)
        animationStarted = true
    }

    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF799DDB), Color(0xFF1B62DB)),
        start = Offset(0f, 0f),
        end = Offset(0f, 1000f)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient),
        contentAlignment = Alignment.CenterStart
    ) {
        Bubbles()
        Fish(
            modifier = Modifier
                .size(200.dp)
                .offset { IntOffset(fishOffsetX.roundToInt(), (fishOffsetY * sin(2 * Math.PI * frequency * (fishOffsetX / screenWidth))).roundToInt()) }
                .graphicsLayer(rotationZ = fishRotation)
        )
        Bubbles()
    }
}

@Composable
fun Bubbles() {
    val bubbleCount = 10
    val infiniteTransition = rememberInfiniteTransition()
    val screenWidth = 1000.dp.toPx()
    val screenHeight = 1000.dp.toPx()

    // Animate bubbles
    repeat(bubbleCount) { index ->
        val bubbleOffsetX by infiniteTransition.animateFloat(
            initialValue = (0..screenWidth.toInt()).random().toFloat(),
            targetValue = (0..screenWidth.toInt()).random().toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = (3000..7000).random(), easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "Bubble Offset X $index"
        )

        val bubbleOffsetY by infiniteTransition.animateFloat(
            initialValue = screenHeight,
            targetValue = -50f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = (3000..7000).random(), easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
            label = "Bubble Offset Y $index"
        )

        Box(
            modifier = Modifier
                .size(30.dp)
                .offset { IntOffset(bubbleOffsetX.roundToInt(), bubbleOffsetY.roundToInt()) }
                .drawBehind {
                    drawBubble(
                        size = size,
                        center = center,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                }
        )
    }
}

private fun DrawScope.drawBubble(size: Size, center: Offset, color: Color) {
    val bubbleRadius = size.minDimension / 2f
    drawCircle(
        color = color,
        radius = bubbleRadius,
        center = center,
        style = Stroke(width = 1f)
    )
    drawCircle(
        color = color.copy(alpha = 0.4f),
        radius = bubbleRadius,
        center = center,
        style = Fill
    )
    drawCircle(
        color = Color.White.copy(alpha = 0.2f),
        radius = bubbleRadius / 2f,
        center = Offset(center.x + 10.dp.toPx(), center.y - 10.dp.toPx())
    )
}

@Composable
fun Dp.toPx(): Float {
    val density = LocalDensity.current.density
    return this.value * density
}

@Composable
fun Fish(modifier: Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.fish_svgrepo_com),
            contentDescription = "fish"
        )
    }
}

@Serializable
object FishCanvas

@Preview(showBackground = true)
@Composable
fun FishPreview() {
    FishCanvas()
}
