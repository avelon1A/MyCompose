package com.bosch.composewithkotlin20.data.model.data

import AnimatedVisibilityExample
import FishCanvas
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.AnimatedChildren
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.AnimatedContentScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.AnimatedContentScreenIcons
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.BouncingBallScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.ImageMover
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.LottieAnimationScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.SharedElementScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.SlideBox
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.VectorAnimationScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.WaterBottelCanvas
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.BottomNavigationBar
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.CameraScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.CircularIndicatorScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.CoilScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.DropDown
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.GoogleButtonScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.GradientScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.LoginScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.MusicScreenContent
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.OnBoardingScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.ScreenC
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.Seekbar
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.Snackbar
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.SwipeToDelete
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.TextScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.TextSelectable
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.Textfield
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.ThirdScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.TypeSafeNavigation
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasA
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasLine
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasMovement
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasOval
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasOverlap
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.WaterBottleScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.supabase.SupaBaseMainScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.supabase.SupabaseVideoPlayer
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
    ButtonInfo("Swipe To Delete", SwipeToDelete),
    ButtonInfo("Animation Position", ImageMover),
)
val animationList = listOf(
    ButtonInfo("lottie ", LottieAnimationScreen),
    ButtonInfo("Animated Visibility", AnimatedVisibilityExample),
    ButtonInfo("Animate enter and exit for children", AnimatedChildren),
    ButtonInfo("Animated ContentScreen", AnimatedContentScreen),
    ButtonInfo("vector animation", VectorAnimationScreen),
    ButtonInfo("Animated ContentScreen Icons", AnimatedContentScreenIcons),
    ButtonInfo("Bouncy Ball", BouncingBallScreen),
    ButtonInfo("SharedElementScreen", SharedElementScreen),
    ButtonInfo("Slide animation", SlideBox),
)
val canvasList = listOf(
    ButtonInfo("line canvas ", CanvasLine),
    ButtonInfo("A canvas ", CanvasA),
    ButtonInfo("Oval canvas ", CanvasOval),
    ButtonInfo("Movement in canvas ", CanvasMovement),
    ButtonInfo("Overlap in Canvas ", CanvasOverlap),
    ButtonInfo("Water bottel ", WaterBottleScreen),
    ButtonInfo("fish  ", FishCanvas),
    ButtonInfo("water bottel  ", WaterBottelCanvas),
)
val appList = listOf(
    ButtonInfo("Music Player ", MusicScreenContent),
    ButtonInfo("Todo App", TodoScreen),
    ButtonInfo("Onboarding Tutorial", OnBoardingScreen),
    ButtonInfo("Login Screen", LoginScreen),
    ButtonInfo("Supabase Screen", SupaBaseMainScreen),
    ButtonInfo("SupabaseVideo Screen", SupabaseVideoPlayer),

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

