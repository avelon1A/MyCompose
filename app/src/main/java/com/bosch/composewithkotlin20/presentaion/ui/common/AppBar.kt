package com.bosch.composewithkotlin20.presentaion.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bosch.composewithkotlin20.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(icon: Int, navController: NavController) {
    val uiMode = MaterialTheme.colorScheme.onSurface
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {

        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = { performActionBasedOnIcon(icon, navController) }) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = "Localized description",
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(uiMode)
                    )
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Image(
                        painter = painterResource(id = R.drawable.__icon__me_),
                        contentDescription = "Localized description",
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(uiMode)
                    )
                }

            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

    }
}

fun performActionBasedOnIcon(icon: Int, navController: NavController) {
    when (icon) {
        R.drawable.arrow_back -> {
            navController.popBackStack()
        }

        R.drawable.__icon__hamburger_button_ -> {

        }
    }
}
