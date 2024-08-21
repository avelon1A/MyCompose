package com.bosch.composewithkotlin20.presentaion.navigation


import AnimatedVisibilityExample
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.LottieAnimationScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.NavigationHome
import com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens.VectorAnimationScreen
import com.bosch.composewithkotlin20.presentaion.ui.todo.TodoScreen
import com.bosch.composewithkotlin20.presentaion.ui.todo.TodoViewModel
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.AudioViewModel
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.OnBoardingViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavHost(navController: NavHostController,modifier: Modifier = Modifier,startDestination: Any) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        composable<HomeScreen> {
            HomeScreen(navController = navController)
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
            val audioViewModel: AudioViewModel = koinViewModel { parametersOf(SavedStateHandle()) }
            MusicScreenContent( audioViewModel)
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
            NewHome(navController,Modifier)
        }
        composable<NavigationHome> {
            NavigationHome(navController)
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
            TypeSafeNavigationSecond(name)
        }
        
    }
    
   
}

