package com.bosch.composewithkotlin20.presentaion.ui.screen

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bosch.composewithkotlin20.R
import com.bosch.composewithkotlin20.presentaion.ui.common.AppBar
import com.bosch.composewithkotlin20.presentaion.ui.common.Buttons
import com.bosch.composewithkotlin20.presentaion.ui.screen.supabase.SupaBaseMainScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.supabase.SupabaseVideoPlayer
import com.bosch.composewithkotlin20.presentaion.ui.todo.TodoScreen
import kotlinx.serialization.Serializable


@Composable
fun AppsHomeScreen(navController: NavController) {
    val appListNew = listOf(
        MainScreenButtons(
            "Music Player  ",
            MusicScreenContent,
            Color.White,
            R.drawable.music_svgrepo_com
        ),
        MainScreenButtons(
            "Todo App",
            TodoScreen,
            Color.White,
            R.drawable.todo_svgrepo_com__1_
        ),
        MainScreenButtons(
            "Onboarding Tutorial",
            OnBoardingScreen,
            Color.White,
            R.drawable.app_ghtk_svgrepo_com
        ),
        MainScreenButtons(
            "Login Screen",
            LoginScreen,
            Color.White,
            R.drawable.login_svgrepo_com__2_
        ),
        MainScreenButtons(
            "Supabase Screen",
            SupaBaseMainScreen,
            Color.White,
            R.drawable.list_list_svgrepo_com
        ),
        MainScreenButtons(
            "SupabaseVideo Screen",
            SupabaseVideoPlayer,
            Color.White,
            R.drawable.video_svgrepo_com
        )
    )

    Scaffold(topBar = {
        AppBar(R.drawable.arrow_back, navController)
    }, content = { innerPadding ->
        Buttons.AppScreenIcons(appListNew, navController, innerPadding, Modifier.size(100.dp), chunks = 1)
    })

}

@Serializable
object AppsHomeScreen