package com.bosch.composewithkotlin20.presentaion.navigation


import AnimatedVisibilityExample
import FishCanvas
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.bosch.composewithkotlin20.presentaion.ui.screen.BottomNavigationBar
import com.bosch.composewithkotlin20.presentaion.ui.screen.CameraScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.CircularIndicatorScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.CoilScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.DropDown
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
import com.bosch.composewithkotlin20.presentaion.ui.todo.TodoViewModel
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.AudioViewModel
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.OnBoardingViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Any
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            composable<HomeScreen> {
                val name = it.toRoute<HomeScreen>()
                HomeScreen(navController = navController, name)
            }
            composable<ScreenB> {
                ScreenB(navController = navController)
            }
            composable<ScreenC> {
                ScreenC(navController = navController)
            }
            composable<ThirdScreen> {
                ThirdScreen(navController = navController)
            }
            composable<TextScreen> {
                TextScreen()
            }
            composable<TextSelectable> {
                TextSelectable()
            }
            composable<Textfield> {
                Textfield()
            }
            composable<GoogleButtonScreen> {
                GoogleButtonScreen()
            }
            composable<CoilScreen> {
                CoilScreen()
            }
            composable<GradientScreen> {
                GradientScreen()
            }
            composable<CameraScreen> {
                CameraScreen()
            }
            composable<LoginScreen> {
                LoginScreen()
            }
            composable<CircularIndicatorScreen> {
                CircularIndicatorScreen()
            }
            composable<OnBoardingScreen> {
                val viewModel: OnBoardingViewModel = koinViewModel()
                OnBoardingScreen(event = viewModel::OnEvent)
            }
            composable<BottomNavigationBar> {
                BottomNavigationBar()
            }
            composable<MusicScreenContent> {
                val audioViewModel: AudioViewModel =
                    koinViewModel { parametersOf(SavedStateHandle()) }
                MusicScreenContent(audioViewModel)
            }
            composable<Seekbar> {
                Seekbar()
            }
            composable<Snackbar> {
                Snackbar()
            }
            composable<TodoScreen> {
                val viewModel: TodoViewModel = koinViewModel()
                TodoScreen(viewModel)
            }
            composable<DropDown> {
                DropDown()
            }
            composable<NewHome> {
                NewHome(navController, Modifier)
            }

            composable<LottieAnimationScreen> {
                LottieAnimationScreen()
            }
            composable<AnimatedVisibilityExample> {
                AnimatedVisibilityExample()
            }
            composable<AnimatedChildren> {
                AnimatedChildren()
            }
            composable<AnimatedContentScreen> {
                AnimatedContentScreen()
            }
            composable<VectorAnimationScreen> {
                VectorAnimationScreen()
            }
            composable<TypeSafeNavigation> {
                TypeSafeNavigation(navController)
            }
            composable<TypeSafeNavigationSecond> {
                val name = it.toRoute<TypeSafeNavigationSecond>()
                TypeSafeNavigationSecond(navController, name)
            }
            composable<AnimatedContentScreenIcons> {
                AnimatedContentScreenIcons()
            }
            composable<BouncingBallScreen> {
                BouncingBallScreen()
            }
            composable<CanvasA> {
                CanvasA()
            }
            composable<CanvasLine> {
                CanvasLine()
            }
            composable<CanvasOval> {
                CanvasOval()
            }
            composable<CanvasMovement> {
                CanvasMovement()
            }
            composable<CanvasOverlap> {
                CanvasOverlap()
            }
            composable<WaterBottleScreen> {
                WaterBottleScreen(LocalContext.current)
            }
            composable<FishCanvas> {
                FishCanvas()
            }
            composable<WaterBottelCanvas> {
                WaterBottelCanvas()
            }
            composable<SharedElementScreen> {
                SharedElementScreen(navController, this@SharedTransitionLayout, this@composable)
            }

            composable<DetailsScreen> {
                val item = it.toRoute<DetailsScreen>()
                DetailsScreen(
                    navController = navController, id = item.id, image = item.image, name = item.name, description = item.description,
                    this@SharedTransitionLayout,
                    this@composable
                )
            }
        }

    }

}


