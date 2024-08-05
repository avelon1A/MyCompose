package com.bosch.composewithkotlin20.presentaion.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@Composable
fun TextSelectable() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SelectionContainer {
            Text(text = "Selectable text by default the text on Compose is not selectable ")
            DisableSelection {
                Text(text = "this text cannot be selected ")
            }
            Text(text = "Selectable text ")

        }
    }
}



@Serializable
object TextSelectable