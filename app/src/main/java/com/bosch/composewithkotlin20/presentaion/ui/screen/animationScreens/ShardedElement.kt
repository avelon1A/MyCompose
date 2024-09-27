package com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.bosch.composewithkotlin20.R
import com.bosch.composewithkotlin20.presentaion.ui.common.AppBar
import kotlinx.serialization.Serializable


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedElementScreen(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    val itemlist = listOf(
        Snack("Fish", "fish", R.drawable.fish_svgrepo_com),
        Snack("Robot", "robot", R.drawable.robot_android_svgrepo_com),

        )
    var buttonsEnabled by remember { mutableStateOf(true) }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
      AppBar(navController = navController, icon =R.drawable.arrow_back)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(itemlist) { index, item ->

                Item(image = item.image,
                    name = item.name,
                    description = item.description,
                    index = index,
                    onClick = {

                        if (buttonsEnabled) {
                            buttonsEnabled = false
                            navController.navigate(
                                DetailsScreen(
                                    id = index,
                                    name = item.name,
                                    image = item.image,
                                    description = item.description
                                )
                            ) {
                                launchSingleTop = true
                            }

                        }
                        buttonsEnabled = true

                    },
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedContentScope
                )
            }
        }
    }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Item(
    image: Int, name: String, description: String,
    onClick: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    index: Int
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(50.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(8.dp)
            .clickable { onClick() }
            .clip(RoundedCornerShape(2.dp))
            .background(
                Color.Gray.copy(alpha = 0.4f)
            )
    ) {
        with(sharedTransitionScope) {
            Image(modifier = Modifier.padding(5.dp)
                .size(100.dp)
                .sharedElement(state = rememberSharedContentState(key = "image$index"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { initial, target ->
                       spring(dampingRatio = 0.8f, stiffness = 380f)

                    }
                ), painter = painterResource(id = image),
                contentDescription = description)
            Text(modifier = Modifier.padding(end = 10.dp),
//                .sharedElement(state = rememberSharedContentState(key = "text$index"),
//                    animatedVisibilityScope = animatedVisibilityScope,
//                    boundsTransform = { _, _ ->
//                        tween(durationMillis = 500, easing = LinearEasing)
//
//                    }
//                ),
                text = name, fontSize = 20.sp)
        }
    }
}


@Serializable
object SharedElementScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailsScreen(
    navController: NavHostController,
    id: Int,
    image: Int,
    name: String,
    description: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    with(sharedTransitionScope) {
        Column(Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,


            ) {
            Image(
                painterResource(id = image),
                contentDescription = description,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(400.dp)
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = "image$id"),
                        animatedVisibilityScope = animatedContentScope,
                                boundsTransform = { _, _ ->
                                    spring(dampingRatio = 0.8f, stiffness = 380f)
                        }
                    )
                    .aspectRatio(1f)
                    .fillMaxWidth()
            )
            Text(
                text = name, fontSize = 44.sp,
//                modifier =
//                Modifier.sharedElement(
//                        sharedTransitionScope.rememberSharedContentState(key = "text$id"),
//                        animatedVisibilityScope = animatedContentScope,
//                    boundsTransform = { _, _ ->
//                        tween(durationMillis = 500, easing = LinearOutSlowInEasing)
//                    }
//                )
            )
        }
    }
}

@Serializable
data class DetailsScreen(
    val id: Int,
    val name: String,
    val image: Int,
    val description: String
)


data class Snack(
    val name: String,
    val description: String,
    @DrawableRes val image: Int
)