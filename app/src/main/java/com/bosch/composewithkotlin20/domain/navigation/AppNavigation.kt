package com.bosch.composewithkotlin20.domain.navigation

/**
 * This is the interface for navigation between screens.
 *
 */

interface AppNavigator {
    fun navigateToHome()
    fun navigateToScreenB()
    fun navigateToScreenC()
    fun navigateToThirdScreen()
    fun navigateToTextScreen()
    fun navigateToTextSelectableScreen()
    fun navigateToTextfieldScreen()
    fun navigateToGoogleButtonScreen()
    fun navigateToCoilScreen()
    fun navigateToGradientScreen()
    fun navigateToCameraScreen()
    fun navigateToLoginScreen()
    fun navigateToCircularIndicatorScreen()
    fun navigateToOnBoardingScreen()
    fun navigateToBottomNavigationBar()
    fun navigateToMusicScreenContent()
    fun navigateToSeekbarScreen()
    fun navigateToSnackbarScreen()
    fun navigateToTodoScreen()
    fun navigateToDropDownScreen()
    fun navigateToNewHome()
    fun navigateToLottieAnimationScreen()
    fun navigateToAnimatedVisibilityExample()
    fun navigateToAnimatedChildren()
    fun navigateToAnimatedContentScreen()
    fun navigateToVectorAnimationScreen()
    fun navigateToTypeSafeNavigationScreen()
    fun navigateToTypeSafeNavigationSecondScreen(name: String)
    fun navigateToAnimatedContentScreenIcons()
    fun navigateToBouncingBallScreen()
    fun navigateToCanvasAScreen()
    fun navigateToCanvasLineScreen()
    fun navigateToCanvasOvalScreen()
    fun navigateToCanvasMovementScreen()
    fun navigateToCanvasOverlapScreen()
    fun navigateToWaterBottleScreen()
    fun navigateToFishCanvasScreen()
    fun navigateToWaterBottelCanvasScreen()
    fun navigateToSharedElementScreen()
    fun navigateToDetailsScreen(id: Int, image: Int, name: String, description: String)
}
