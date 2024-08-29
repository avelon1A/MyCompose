package com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import kotlinx.serialization.Serializable


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedElementScreen(navController: NavController,
                        sharedTransitionScope: SharedTransitionScope,
                        animatedContentScope: AnimatedContentScope
 ) {
    val itemlist = listOf(
        Snack("fish", "fish", R.drawable.fish_svgrepo_com),
        Snack("robot", "robot", R.drawable.robot_android_svgrepo_com),

    )

    with(sharedTransitionScope){  Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(itemlist) { index, item ->

                Item(image = item.image, name = item.name,description = item.description,
                    onClick = {
                        navController.navigate(DetailsScreen(id = item.image, name = item.name, image = item.image, description = item.description))
                    },
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedContentScope
                )
            }
        }
    }
        }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Item(
    image: Int, name: String,description: String,
    onClick: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(40.dp),
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
            Image(modifier = Modifier
                .size(100.dp)
                .sharedElement(state = rememberSharedContentState(key = 123),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(1000)

                    }
                ), painter = painterResource(id = image),
                contentDescription = description)
            Text(modifier = Modifier
                .sharedElement(state = rememberSharedContentState(key = 456),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(1000)

                    }
                ),
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
   image:Int,
    name:String,
    description: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    with(sharedTransitionScope) {
        Column(
            Modifier
                .fillMaxSize()
                .clickable {
                    navController.navigate("home")
                }
        ) {
            Image(
                painterResource(id = image),
                contentDescription = description,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = 123),
                        animatedVisibilityScope = animatedContentScope
                    )
                    .aspectRatio(1f)
                    .fillMaxWidth()
            )
            Text(
                text = name, fontSize = 18.sp,
                modifier =
                Modifier
                    .sharedElement(
                        sharedTransitionScope.rememberSharedContentState(key = 456),
                        animatedVisibilityScope = animatedContentScope
                    )
                    .fillMaxWidth()
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