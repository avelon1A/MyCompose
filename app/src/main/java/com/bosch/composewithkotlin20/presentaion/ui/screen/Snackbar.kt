package com.bosch.composewithkotlin20.presentaion.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Composable
fun Snackbar() {
	val snackbarHostState = remember { SnackbarHostState() }
	val coroutineScope = rememberCoroutineScope()
	Scaffold(
		snackbarHost = { SnackbarHost(snackbarHostState) },
		content = { padding ->
			Box(
				modifier = Modifier
					.fillMaxSize()
					.padding(padding),
				contentAlignment = Alignment.Center
			) {
				Button(onClick = {
					coroutineScope.launch {
						snackbarHostState.showSnackbar(
							message = "This is a Snack bar message",
							actionLabel = "Dismiss",
							duration = SnackbarDuration.Short
						)
					}
				}) {
					Text("Show Snack bar")
				}
			}
		}
	)
	
}
@Serializable
object Snackbar