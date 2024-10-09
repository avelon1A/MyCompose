package com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown() {
	var expanded by remember { mutableStateOf(false) }
	var selectedItem by remember { mutableStateOf("Select an option") }
	val items = listOf("Option 1", "Option 2", "Option 3")
	
	Box(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		contentAlignment = Alignment.Center
	) {
		ExposedDropdownMenuBox(
			expanded = expanded,
			onExpandedChange = { expanded = !expanded }
		) {
			TextField(
				value = selectedItem,
				onValueChange = {},
				readOnly = true,
				label = { Text("Choose an option") },
				trailingIcon = {
					ExposedDropdownMenuDefaults.TrailingIcon(
						expanded = expanded
					)
				},
				modifier = Modifier
					.menuAnchor()
					.fillMaxWidth(0.9f) // Adjust width as needed
					.clickable { expanded = true }
			)
			
			ExposedDropdownMenu(
				expanded = expanded,
				onDismissRequest = { expanded = false }
			) {
				items.forEach { item ->
					DropdownMenuItem(
						text = { Text(item) },
						onClick = {
							selectedItem = item
							expanded = false
						}
					)
				}
			}
		}
	}
}

@Serializable
object DropDown
