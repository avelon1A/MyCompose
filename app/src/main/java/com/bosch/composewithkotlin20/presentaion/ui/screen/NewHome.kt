package com.bosch.composewithkotlin20.presentaion.ui.screen


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bosch.composewithkotlin20.R
import com.bosch.composewithkotlin20.presentaion.ui.common.AppBar
import com.bosch.composewithkotlin20.util.Const.ANIMATION_SCREEN
import com.bosch.composewithkotlin20.util.Const.CANVAS_SCREEN
import com.bosch.composewithkotlin20.util.Const.UI_SCREEN
import kotlinx.serialization.Serializable

@Composable
fun NewHome(navController: NavController, modifier: Modifier) {
    val buttonList = listOf(
        MainScreenButtons(
            "ui ",
            HomeScreen(items = UI_SCREEN),
            Color.Red,
            R.drawable.sun_svgrepo_com
        ),
        MainScreenButtons("Apps", ScreenC, Color.Blue, R.drawable.app_store_svgrepo_com),
        MainScreenButtons(
            "Animation",
            HomeScreen(ANIMATION_SCREEN),
            Color.Magenta,
            R.drawable.animation_gif_image_svgrepo_com
        ),
        MainScreenButtons("Text", TextScreen, Color.Green, R.drawable.text_view_svgrepo_com),
        MainScreenButtons(
            "canvas",
            HomeScreen(CANVAS_SCREEN),
            Color.Yellow,
            R.drawable.canvas_easel_svgrepo_com
        )
    )

    Scaffold(topBar = {
        AppBar(R.drawable.__icon__hamburger_button_, navController)
    }, content = { innerPadding ->
        HomeButton(buttonList, navController, innerPadding, Modifier)
    })
}

data class MainScreenButtons(val title: String, val route: Any, val color: Color, val image: Int)

@Composable
fun HomeButton(
    buttonList: List<MainScreenButtons>,
    navController: NavController,
    innerPadding: PaddingValues,
    modifier: Modifier
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(buttonList.chunked(2), key = { rowButtons ->
            rowButtons.hashCode()
        }) { rowButtons ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                rowButtons.forEach {

                    HomeCustomButton(
                        modifier = modifier,
                        text = it.title,
                        onClick = { navController.navigate(it.route) },
                        color = it.color,
                        image = it.image,

                        )
                }
            }
        }


    }
}

@Composable
fun HomeCustomButton(
    modifier: Modifier, text: String, onClick: () -> Unit, color: Color, image: Int
) {
    Card(
        modifier = modifier
            .size(
                height = 200.dp, width = 200.dp
            )
            .padding(16.dp)
            .border(
                BorderStroke(.1.dp, color.copy(0.5f)), shape = RoundedCornerShape(2.dp)
            )
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(2.dp),
                clip = false,
                ambientColor = color,
                spotColor = color
            )
            .clickable(onClick = onClick,
                indication = rememberRipple(color = color),
                interactionSource = remember { MutableInteractionSource() })
            .background(MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(90.dp),
                painter = painterResource(id = image),
                contentDescription = null,
            )

            Text(
                text = text, color = color.copy(0.5f), fontSize = 15.sp
            )
        }


    }
}


@Preview(
    name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun DarkModeButton() {
    HomeCustomButton(
        modifier = Modifier,
        text = "Auto",
        onClick = { },
        color = Color.Red,
        image = R.drawable.sun_svgrepo_com
    )
}


@Serializable
object NewHome