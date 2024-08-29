package com.bosch.composewithkotlin20.presentaion.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bosch.composewithkotlin20.R
import com.bosch.composewithkotlin20.data.model.data.ButtonInfo
import com.bosch.composewithkotlin20.data.model.data.getItemList
import com.bosch.composewithkotlin20.presentaion.ui.common.AppBar
import com.bosch.composewithkotlin20.util.Const.CANVAS_SCREEN
import kotlinx.serialization.Serializable

@Composable
fun HomeScreen(navController: NavController, args: HomeScreen) {
    val buttonList = getItemList(args.items)
    Scaffold(
        topBar = {
            AppBar(R.drawable.arrow_back, navController)
        },
        content = { innerPadding ->
            ButtonGrid(buttonList, navController, innerPadding)
        }
    )
}

@Composable
fun ButtonGrid(list: List<ButtonInfo>, navController: NavController, innerPadding: PaddingValues) {
    var buttonsEnabled by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(list) { buttonItem ->
            CustomButton(
                text = buttonItem.title,
                onClick = {
                    if (buttonsEnabled) {
                        buttonsEnabled = false
                        navController.navigate(buttonItem.route) {
                            launchSingleTop = true
                        }
                        navController.currentBackStackEntry?.lifecycle?.addObserver(
                            object : DefaultLifecycleObserver {
                                override fun onResume(owner: LifecycleOwner) {
                                    buttonsEnabled = true
                                    owner.lifecycle.removeObserver(this)
                                }
                            }
                        )
                    }
                },

            )
        }
    }
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(40.dp)
            .background(MaterialTheme.colorScheme.surface)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = text,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.scrim
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFCECECE))
    )
}


@Preview
@Composable
fun HomeScreenPreview() {
    val fakeNavController = fakeNavController()
    HomeScreen(navController = fakeNavController, HomeScreen(CANVAS_SCREEN))
}

@Composable
fun fakeNavController(): NavController {
    return rememberNavController()
}


@Serializable
data class HomeScreen(
    val items: String
)