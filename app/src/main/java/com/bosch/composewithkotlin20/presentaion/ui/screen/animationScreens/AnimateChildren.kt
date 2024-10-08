package com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bosch.composewithkotlin20.data.model.data.Animations
import com.bosch.composewithkotlin20.data.model.data.OutAnimationOptions
import com.bosch.composewithkotlin20.data.model.data.inAnimationOptions
import kotlinx.serialization.Serializable

@Composable
fun AnimatedChildren() {
    var visible by remember { mutableStateOf(true) }
    val expanded = remember { mutableStateOf(false) }
    val expanded1 = remember { mutableStateOf(false) }
    val animationIn = remember { mutableStateOf(Animations("fadeIn", enterAnim = fadeIn())) }
    val animationOut = remember { mutableStateOf(Animations("fadeOut", exitAnim = fadeOut())) }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Dropdown(expanded, animationIn, inAnimationOptions, "Enter Animation")
        Dropdown(expanded1, animationOut, OutAnimationOptions, "Exit Animation")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { visible = !visible }) {
            Text(text = "Toggle Animation")
        }
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray)) {
                Box(
                    Modifier
                        .align(Alignment.Center)
                        .animateEnterExit(
                            enter = animationIn.value.enterAnim!!,
                            exit = animationOut.value.exitAnim!!
                        )
                        .sizeIn(minWidth = 256.dp, minHeight = 64.dp)
                        .background(Color.Red)
                ) {

                }
            }

        }


    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun Dropdown(
    expanded: MutableState<Boolean>,
    selectedOption: MutableState<Animations>,
    options: List<Animations>,
    lable: String
) {
    ExposedDropdownMenuBox(
        expanded = expanded.value,
        onExpandedChange = { expanded.value = !expanded.value },
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedOption.value.name,
            onValueChange = {},
            label = { Text(lable) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded.value = !expanded.value }
                .menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option.name) },
                    onClick = {
                        expanded.value = false
                        selectedOption.value = option
                    }
                )
            }

        }
    }
}

@Serializable
object AnimatedChildren

@Preview(showBackground = true)
@Composable
fun PreviewAnimatedVisibilityExample() {
    AnimatedChildren()
}
