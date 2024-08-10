package com.bosch.composewithkotlin20.presentaion.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bosch.composewithkotlin20.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp)
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
}
