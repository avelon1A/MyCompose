package com.bosch.composewithkotlin20.presentaion.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bosch.composewithkotlin20.R
import com.bosch.composewithkotlin20.presentaion.ui.todo.TodoScreen
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewHome(navController: NavController, modifier: Modifier) {
    val buttonList = listOf(
        ButtonInfo("ui ", HomeScreen),
        ButtonInfo("Apps", ScreenC),
        ButtonInfo("Animation", ThirdScreen),
        ButtonInfo("Text", TextScreen),

        )

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(8.dp)
            ) {

                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Image(
                                painter = painterResource(id = R.drawable.__icon__hamburger_button_),
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Image(
                                painter = painterResource(id = R.drawable.__icon__me_),
                                contentDescription = "Localized description"
                            )
                        }

                    },
                )

            }
        },
        content = { innerPadding ->
            HomeButton(buttonList, navController, innerPadding, Modifier)
        }
    )
}

@Composable
fun HomeButton(
    buttonList: List<ButtonInfo>,
    navController: NavController,
    innerPadding: PaddingValues,
    modifier: Modifier.Companion
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding)
    ) {

        buttonList.forEach {
            HomeCustomButton(
                modifier = modifier,
                text = it.title,
                onClick = { navController.navigate(it.route) },Color.Red)
        }


    }
}

@Composable
fun HomeCustomButton(modifier: Modifier, text: String, onClick: () -> Unit,color: Color ) {
    Card(
        modifier = modifier.size(
            height = 100.dp,
            width = 100.dp)
            .padding(16.dp)
            .border(
                BorderStroke(.1.dp,color)
                , shape = RoundedCornerShape(2.dp)
            )
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(2.dp),
                clip = false,
                ambientColor = color,
                spotColor = color
            )
            .clickable(
                onClick = onClick
            )
            , shape = RoundedCornerShape(2.dp)
    ) {

    }

}

@Preview
@Composable
fun MyButton() {
    HomeCustomButton(modifier = Modifier, text = "Auto", onClick = { },Color.Red)
}


@Serializable
object NewHome