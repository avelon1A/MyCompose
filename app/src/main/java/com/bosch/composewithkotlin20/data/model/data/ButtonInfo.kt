package com.bosch.composewithkotlin20.data.model.data

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
import com.bosch.composewithkotlin20.presentaion.ui.screen.ScreenB
import com.bosch.composewithkotlin20.presentaion.ui.screen.ScreenC
import com.bosch.composewithkotlin20.presentaion.ui.screen.Seekbar
import com.bosch.composewithkotlin20.presentaion.ui.screen.Snackbar
import com.bosch.composewithkotlin20.presentaion.ui.screen.TextScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.TextSelectable
import com.bosch.composewithkotlin20.presentaion.ui.screen.Textfield
import com.bosch.composewithkotlin20.presentaion.ui.screen.ThirdScreen
import com.bosch.composewithkotlin20.presentaion.ui.screen.TypeSafeNavigation
import com.bosch.composewithkotlin20.presentaion.ui.todo.TodoScreen

data class ButtonInfo(val title: String, val route: Any)


val ButtonList = listOf(
    ButtonInfo("First Example", ScreenB),
    ButtonInfo("Expandable Text", ScreenC),
    ButtonInfo("Box Layout Example", ThirdScreen),
    ButtonInfo("Text Display", TextScreen),
    ButtonInfo("Selectable Text", TextSelectable),
    ButtonInfo("Input Field", Textfield),
    ButtonInfo("Google Sign-In Button", GoogleButtonScreen),
    ButtonInfo("Image Loading with Coil", CoilScreen),
    ButtonInfo("Gradient Background", GradientScreen),
    ButtonInfo("Camera Integration", CameraScreen),
    ButtonInfo("Login Screen", LoginScreen),
    ButtonInfo("Circular Progress Indicator", CircularIndicatorScreen),
    ButtonInfo("Onboarding Tutorial", OnBoardingScreen),
    ButtonInfo("Bottom Navigation Bar", BottomNavigationBar),
    ButtonInfo("Music Player UI", MusicScreenContent),
    ButtonInfo("Custom SeekBar", Seekbar),
    ButtonInfo("Snack bar Notification", Snackbar),
    ButtonInfo("Todo List", TodoScreen),
    ButtonInfo("Dropdown Menu", DropDown),
    ButtonInfo("Type-Safe Navigation", TypeSafeNavigation)

)