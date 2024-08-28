package com.bosch.composewithkotlin20.data.model.data

import AnimatedVisibilityExample
import FishCanvas
import com.bosch.composewithkotlin20.presentaion.ui.screen.BottomNavigationBar
import com.bosch.composewithkotlin20.presentaion.ui.screen.CameraScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.CircularIndicatorScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.CoilScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.DropDown
import com.bosch.composewithkotlin20.presentaion.ui.screen.GoogleButtonScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.GradientScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.LoginScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.MusicScreenContent
import com.bosch.composewithkotlin20.presentaion.ui.screen.OnBoardingScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.ScreenC
import com.bosch.composewithkotlin20.presentaion.ui.screen.Seekbar
import com.bosch.composewithkotlin20.presentaion.ui.screen.Snackbar
import com.bosch.composewithkotlin20.presentaion.ui.screen.TextScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.TextSelectable
import com.bosch.composewithkotlin20.presentaion.ui.screen.Textfield
import com.bosch.composewithkotlin20.presentaion.ui.screen.ThirdScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.TypeSafeNavigation
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.AnimatedChildren
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.AnimatedContentScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.AnimatedContentScreenIcons
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.BouncingBallScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.LottieAnimationScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.VectorAnimationScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasA
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasLine
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasMovement
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasOval
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasOverlap
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.WaterBottleScreen
import com.bosch.composewithkotlin20.presentaion.ui.todo.TodoScreen
import com.bosch.composewithkotlin20.util.Const.ANIMATION_SCREEN
import com.bosch.composewithkotlin20.util.Const.APPS_SCREEN
import com.bosch.composewithkotlin20.util.Const.CANVAS_SCREEN
import com.bosch.composewithkotlin20.util.Const.UI_SCREEN


data class ButtonInfo(val title: String, val route: Any)


val ButtonList = listOf(
    ButtonInfo("Expandable Text", ScreenC),
    ButtonInfo("Box Layout Example", ThirdScreen),
    ButtonInfo("Text Display", TextScreen),
    ButtonInfo("Selectable Text", TextSelectable),
    ButtonInfo("Input Field", Textfield),
    ButtonInfo("Google Sign-In Button", GoogleButtonScreen),
    ButtonInfo("Image Loading with Coil", CoilScreen),
    ButtonInfo("Gradient Background", GradientScreen),
    ButtonInfo("Camera Integration", CameraScreen),
    ButtonInfo("Circular Progress Indicator", CircularIndicatorScreen),
    ButtonInfo("Bottom Navigation Bar", BottomNavigationBar),
    ButtonInfo("Custom SeekBar", Seekbar),
    ButtonInfo("Snack bar Notification", Snackbar),
    ButtonInfo("Dropdown Menu", DropDown),
    ButtonInfo("Type-Safe Navigation", TypeSafeNavigation),
)
val animationList = listOf(
    ButtonInfo("lottie ", LottieAnimationScreen),
    ButtonInfo("Animated Visibility", AnimatedVisibilityExample),
    ButtonInfo("Animate enter and exit for children", AnimatedChildren),
    ButtonInfo("Animated ContentScreen", AnimatedContentScreen),
    ButtonInfo("vector animation", VectorAnimationScreen),
    ButtonInfo("Animated ContentScreen Icons", AnimatedContentScreenIcons),
    ButtonInfo("Bouncy Ball", BouncingBallScreen),
)
val canvasList = listOf(
    ButtonInfo("line canvas ", CanvasLine),
    ButtonInfo("A canvas ", CanvasA),
    ButtonInfo("Oval canvas ", CanvasOval),
    ButtonInfo("Movement in canvas ", CanvasMovement),
    ButtonInfo("Overlap in Canvas ", CanvasOverlap),
    ButtonInfo("Water bottel ", WaterBottleScreen),
    ButtonInfo("fish  ", FishCanvas),
)
val appList = listOf(
    ButtonInfo("Music Player ", MusicScreenContent),
    ButtonInfo("Todo App", TodoScreen),
    ButtonInfo("Onboarding Tutorial", OnBoardingScreen),
    ButtonInfo("Login Screen", LoginScreen),
)


fun getItemList(itemList: String?): List<ButtonInfo> {
    return when (itemList) {
        UI_SCREEN -> ButtonList
        ANIMATION_SCREEN -> animationList
        CANVAS_SCREEN -> canvasList
        APPS_SCREEN -> appList
        else -> emptyList()

    }
}