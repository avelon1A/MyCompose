package com.bosch.composewithkotlin20.presentaion.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bosch.composewithkotlin20.R
import com.bosch.composewithkotlin20.presentaion.ui.common.AppBar
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
        HomeButton(appListNew, navController, innerPadding, Modifier.size(100.dp))
    })

}

@Serializable
object AppsHomeScreen