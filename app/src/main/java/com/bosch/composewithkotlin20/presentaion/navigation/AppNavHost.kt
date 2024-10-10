package com.bosch.composewithkotlin20.presentaion.navigation


import AnimatedVisibilityExample
import FishCanvas
import Websocket
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
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.AnimatedChildren
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.AnimatedContentScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.AnimatedContentScreenIcons
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.BouncingBallScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.DetailsScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.ImageMover
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.LottieAnimationScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.SharedElementScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.SlideBox
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.VectorAnimationScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.WaterBottelCanvas
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.AppsHomeScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.BottomNavigationBar
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.CameraScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.CircularIndicatorScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.CoilScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.DropDown
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.GoogleButtonScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.GradientScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.GridButtonScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.HomeScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.LoginScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.MusicScreenContent
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.NewHome
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.OnBoardingScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.ScreenB
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.ScreenC
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.Seekbar
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.Snackbar
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.SwipeToDelete
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.TapToExpandScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.TextScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.TextSelectable
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.Textfield
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.ThirdScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.TypeSafeNavigation
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.TypeSafeNavigationSecond
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasA
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasLine
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasMovement
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasOval
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.CanvasOverlap
import com.bosch.composewithkotlin20.presentaion.ui.screen.canvas.WaterBottleScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.supabase.SupaBaseMainScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.supabase.SupaBaseViewModel
import com.bosch.composewithkotlin20.presentaion.ui.screen.supabase.SupabaseVideoPlayer
import com.bosch.composewithkotlin20.presentaion.ui.screen.supabase.SupabaseVideoPlayerViewModel
import com.bosch.composewithkotlin20.presentaion.ui.todo.TodoScreen
import com.bosch.composewithkotlin20.presentaion.ui.todo.TodoViewModel
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.AudioViewModel
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.OnBoardingViewModel
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.SwipeToDeleteViewModel
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.WebSocketViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Any,
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
                GridButtonScreen(navController = navController)
            }
            composable<ScreenC> {
                TapToExpandScreen(navController = navController)
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
            composable<AppsHomeScreen> {
                AppsHomeScreen(navController)
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
                    navController = navController,
                    id = item.id,
                    image = item.image,
                    name = item.name,
                    description = item.description,
                    this@SharedTransitionLayout,
                    this@composable
                )
            }
            composable<SupaBaseMainScreen> {
                val viewModel: SupaBaseViewModel = koinViewModel()
                SupaBaseMainScreen(viewModel)
            }
            composable<SupabaseVideoPlayer> {
                val viewModel: SupabaseVideoPlayerViewModel = koinViewModel()
                SupabaseVideoPlayer(navController, viewModel)
            }
            composable<SwipeToDelete> {
                val viewModel: SwipeToDeleteViewModel = koinViewModel()
                SwipeToDelete(viewModel)
            }
            composable<ImageMover> {
                ImageMover()
            }
            composable<Websocket> {
                val viewModel: WebSocketViewModel = koinViewModel()
                Websocket(viewModel)
            }
            composable<SlideBox> {
                SlideBox()
            }
        }

    }

}


