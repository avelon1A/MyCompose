package com.bosch.composewithkotlin20.data.model.data

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.unit.IntOffset

data class Animations(
    val name: String,
    val enterAnim: EnterTransition? = null,
    val exitAnim: ExitTransition? = null
)

val inAnimationOptions = listOf(
    Animations("fadeIn", enterAnim = fadeIn()),
    Animations("slideIn", enterAnim = slideIn(initialOffset = { fullSize -> IntOffset(-fullSize.width, 0) })),
    Animations("slideInHorizontally", enterAnim = slideInHorizontally()),
    Animations("slideInVertically", enterAnim = slideInVertically()),
    Animations("scaleIn", enterAnim = scaleIn()),
    Animations("expandIn", enterAnim = expandIn()),
    Animations("expandHorizontally", enterAnim = expandHorizontally())
)
val OutAnimationOptions = listOf(
    Animations("fadeOut", exitAnim = fadeOut()),
    Animations("slideOut", exitAnim = slideOut(targetOffset = { fullSize -> IntOffset(-fullSize.width, 0) })),
    Animations("slideOutHorizontally", exitAnim = slideOutHorizontally()),
    Animations("slideOutVertically", exitAnim = slideOutVertically()),
    Animations("scaleOut", exitAnim = scaleOut()),
    Animations("expandOut", exitAnim = shrinkOut()),
    Animations("shrinkHorizontally", exitAnim = shrinkHorizontally()),
    Animations("shrinkVertically", exitAnim = shrinkVertically())
)