import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.IntOffset
import com.bosch.composewithkotlin20.R
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

@Composable
fun FishCanvas() {
    var animationStarted by remember { mutableStateOf(false) }

  
    val screenWidth = 1000
    val fishWidth = 200.dp.toPx() 
    val targetPosition = (screenWidth - fishWidth) / 2 

    val transition = updateTransition(targetState = animationStarted, label = "Fish Transition")

    val fishOffsetX by transition.animateFloat(
        label = "Fish Offset X",
        transitionSpec = {
            if (animationStarted) {
                tween(durationMillis = 6000, easing = LinearEasing)
            } else {
                spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessLow)
            }
        }
    ) { started ->
        if (started) targetPosition else 0f
    }

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
        Fish(
            modifier = Modifier
                .size(200.dp)
                .offset { IntOffset(fishOffsetX.roundToInt(), 0) } 
        )
    }
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
