package com.bosch.composewithkotlin20.presentaion.navigation

import AnimatedVisibilityExample
import FishCanvas
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.bosch.composewithkotlin20.domain.navigation.AppNavigator
import com.bosch.composewithkotlin20.presentaion.ui.screen.BottomNavigationBar
import com.bosch.composewithkotlin20.presentaion.ui.screen.CameraScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.CircularIndicatorScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.CoilScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.DropDown
import com.bosch.composewithkotlin20.presentaion.ui.screen.GoogleButton
import com.bosch.composewithkotlin20.presentaion.ui.screen.GoogleButtonScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.GradientScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.HomeScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.LoginScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.MusicScreenContent
import com.bosch.composewithkotlin20.presentaion.ui.screen.NewHome
import com.bosch.composewithkotlin20.presentaion.ui.screen.OnBoardingScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.ScreenB
import com.bosch.composewithkotlin20.presentaion.ui.screen.ScreenC
import com.bosch.composewithkotlin20.presentaion.ui.screen.Seekbar
import com.bosch.composewithkotlin20.presentaion.ui.screen.Snackbar
import com.bosch.composewithkotlin20.presentaion.ui.screen.TextScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.TextSelectable
import com.bosch.composewithkotlin20.presentaion.ui.screen.Textfield
import com.bosch.composewithkotlin20.presentaion.ui.screen.ThirdScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.TypeSafeNavigation
import com.bosch.composewithkotlin20.presentaion.ui.screen.TypeSafeNavigationSecond
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.AnimatedChildren
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.AnimatedContentScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.AnimatedContentScreenIcons
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.BouncingBallScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.DetailsScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.LottieAnimationScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.SharedElementScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.VectorAnimationScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.WaterBottelCanvas
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasA
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasLine
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasMovement
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasOval
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasOverlap
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.WaterBottleScreen
import com.bosch.composewithkotlin20.presentaion.ui.todo.TodoScreen
import com.bosch.composewithkotlin20.util.Const.UI_SCREEN

class AppNavigationImp(
   val  navController: NavHostController
): AppNavigator {
    override fun navigateToHome() {
        navController.navigate( HomeScreen(UI_SCREEN))
    }

    override fun navigateToScreenB() {
        navController.navigate( ScreenB)
    }

    override fun navigateToScreenC() {
        navController.navigate( ScreenC)
    }

    override fun navigateToThirdScreen() {
        navController.navigate( ThirdScreen)
    }

    override fun navigateToTextScreen() {
        navController.navigate( TextScreen)
    }

    override fun navigateToTextSelectableScreen() {
        navController.navigate( TextSelectable)
    }

    override fun navigateToTextfieldScreen() {
        navController.navigate( Textfield)
    }

    override fun navigateToGoogleButtonScreen() {
        navController.navigate( GoogleButtonScreen)
    }

    override fun navigateToCoilScreen() {
        navController.navigate( CoilScreen)
    }

    override fun navigateToGradientScreen() {
        navController.navigate( GradientScreen)
    }

    override fun navigateToCameraScreen() {
        navController.navigate( CameraScreen)
    }

    override fun navigateToLoginScreen() {
        navController.navigate( LoginScreen)
    }

    override fun navigateToCircularIndicatorScreen() {
        navController.navigate( CircularIndicatorScreen)
    }

    override fun navigateToOnBoardingScreen() {
        navController.navigate( OnBoardingScreen)}

    override fun navigateToBottomNavigationBar() {
        navController.navigate( BottomNavigationBar)
    }

    override fun navigateToMusicScreenContent() {
        navController.navigate( MusicScreenContent)
    }

    override fun navigateToSeekbarScreen() {
        navController.navigate( Seekbar)
    }

    override fun navigateToSnackbarScreen() {
        navController.navigate( Snackbar)
    }

    override fun navigateToTodoScreen() {
        navController.navigate( TodoScreen)
    }

    override fun navigateToDropDownScreen() {
        navController.navigate( DropDown)
    }

    override fun navigateToNewHome() {
        navController.navigate( NewHome)
    }

    override fun navigateToLottieAnimationScreen() {
        navController.navigate( LottieAnimationScreen)
    }

    override fun navigateToAnimatedVisibilityExample() {
        navController.navigate( AnimatedVisibilityExample)
    }

    override fun navigateToAnimatedChildren() {
        navController.navigate( AnimatedChildren)
    }

    override fun navigateToAnimatedContentScreen() {
        navController.navigate( AnimatedContentScreen)
    }

    override fun navigateToVectorAnimationScreen() {
        navController.navigate( VectorAnimationScreen)
    }

    override fun navigateToTypeSafeNavigationScreen() {
        navController.navigate( TypeSafeNavigation)
    }

    override fun navigateToTypeSafeNavigationSecondScreen(name: String) {
        navController.navigate( TypeSafeNavigationSecond(name))
    }

    override fun navigateToAnimatedContentScreenIcons() {
        navController.navigate( AnimatedContentScreenIcons)
    }

    override fun navigateToBouncingBallScreen() {
        navController.navigate( BouncingBallScreen)
    }

    override fun navigateToCanvasAScreen() {
        navController.navigate( CanvasA)
    }

    override fun navigateToCanvasLineScreen() {
        navController.navigate( CanvasLine)
    }

    override fun navigateToCanvasOvalScreen() {
        navController.navigate( CanvasOval)
    }

    override fun navigateToCanvasMovementScreen() {
        navController.navigate( CanvasMovement)
    }

    override fun navigateToCanvasOverlapScreen() {
        navController.navigate( CanvasOverlap)    }

    override fun navigateToWaterBottleScreen() {
        navController.navigate( WaterBottleScreen)
    }

    override fun navigateToFishCanvasScreen() {
        navController.navigate( FishCanvas)
    }

    override fun navigateToWaterBottelCanvasScreen() {
        navController.navigate( WaterBottelCanvas)
    }

    override fun navigateToSharedElementScreen() {
        navController.navigate( SharedElementScreen)
    }

    override fun navigateToDetailsScreen(
        id: Int,
        image: Int,
        name: String,
        description: String
    ) {
        navController.navigate( DetailsScreen(id= id, name, image, description))
    }
}