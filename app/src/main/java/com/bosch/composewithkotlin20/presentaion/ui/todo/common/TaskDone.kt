package com.bosch.composewithkotlin20.presentaion.ui.todo.common

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bosch.composewithkotlin20.presentaion.ui.todo.Tasks
import com.bosch.composewithkotlin20.util.DateUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskDialog(onDismiss: () -> Unit, onSave: (Tasks) -> Unit) {
	var taskName by remember { mutableStateOf("") }
	var taskDetails by remember { mutableStateOf("") }
	var taskEndDate by remember { mutableStateOf("") }
	var task = remember { mutableStateOf(Tasks(0, "", "", "", emptyList())) }
	var showDialog by remember { mutableStateOf(false) }
	val datePickerDialog = rememberDatePickerState()
	val launcher =
		rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
			val imageList = task.value.taskFiles.toMutableList()
			if (imageList.size < 3) {
				imageList.add(uri.toString())
				task.value = task.value.copy(taskFiles = imageList)
			}
		}
	
	Dialog(onDismissRequest = onDismiss) {
		ConstraintLayout(
			modifier = Modifier
				.fillMaxWidth()
				.wrapContentHeight()
				.padding(16.dp)
				.background(Color.White, shape = RoundedCornerShape(16.dp))
				.padding(16.dp)
		
		) {
			val (title, foreColumn) = createRefs()
			
			Text(text = "Add Task", modifier = Modifier
				.fillMaxWidth()
				.constrainAs(title) {
					top.linkTo(parent.top)
					start.linkTo(parent.start)
					end.linkTo(parent.end)
					height = Dimension.fillToConstraints
				})
			Column(modifier = Modifier.constrainAs(foreColumn) {
				top.linkTo(title.bottom, 10.dp)
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				height = Dimension.fillToConstraints
				width = Dimension.wrapContent
			}) {
				
				TodoTextField(
					label = "Task Name",
					value = task.value.taskName,
					onValueChange = { task.value = task.value.copy(taskName = it) },
					placeholder = " ",
					modifier = Modifier.fillMaxWidth()
				)
				TodoTextField(
					label = "Task Details",
					value = task.value.taskDetails,
					onValueChange = { task.value = task.value.copy(taskDetails = it) },
					placeholder = " ",
					modifier = Modifier.fillMaxWidth()
				)
				
				Box(
					modifier = Modifier
						.fillMaxWidth()
						.padding(15.dp)
						.clickable {
							showDialog = !showDialog
						}
				) {
					Text(
						text = task.value.taskEndDate.ifEmpty { "time?" },
						modifier = Modifier
							.fillMaxWidth()
							.clickable {
								showDialog = !showDialog
							}
					)
				}
				
				if (showDialog) {
					TodoDatePicker(onDismissRequest = {
						showDialog = false
					}, positiveClick = {
						datePickerDialog.selectedDateMillis?.let {
							task.value = task.value.copy(
								taskEndDate = DateUtils.formattedDate(it, DateUtils.DATE_FORMAT)
							)
						}
						showDialog = false
					}, negativeClick = {
									   showDialog = false
					}, dataState = datePickerDialog
					
					)
				}
				Row(
					modifier = Modifier
						.fillMaxWidth()
						.padding()
						.background(Color.Transparent)
						.padding(vertical = 10.dp)
				) {
					if (task.value.taskFiles.size < 3) {
						Card(
							modifier = Modifier
								.height(50.dp)
								.width(50.dp)
								.padding(5.dp)
								.background(Color.Transparent)
								.clickable {
									launcher.launch("image/*")
									
								},
							border = BorderStroke(1.dp, color = Color.Black),
							
							) {
							Icon(
								imageVector = Icons.Filled.Add, contentDescription = "Add",
								modifier = Modifier
									.fillMaxSize()
									.padding(10.dp)
							)
							
						}
					}
					LazyRow {
						items(task.value.taskFiles.size) { index ->
							ImageCard(url = task.value.taskFiles[index]) {
								removeImage(task, index)
							}
						}
					}
					Box(
						modifier = Modifier.fillMaxWidth(),
						contentAlignment = Alignment.CenterEnd
					) {
						Button(onClick = {
							onSave(task.value)
						}, modifier = Modifier.padding(10.dp)) {
							Text(text = "ADD")
						}
					}
				}
				
			}
		}
	}
}

fun removeImage(task: MutableState<Tasks>, index: Int) {
	val imageList = task.value.taskFiles.toMutableList()
	imageList.removeAt(index)
	task.value = task.value.copy(taskFiles = imageList)
	
}

@Composable
fun ImageCard(url: String, onRemove: () -> Unit) {
	
	Card(
		modifier = Modifier
			.height(50.dp)
			.width(50.dp)
			.padding(5.dp)
			.background(Color.Transparent),
		border = BorderStroke(1.dp, color = Color.Black),
		
		) {
		Box(
			modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
		) {
			ThemeImageView(url = url, modifier = Modifier.fillMaxWidth())
			
			Icon(
				imageVector = Icons.Filled.Close, contentDescription = "Close", tint = Color.Red,
				modifier = Modifier
					.height(20.dp)
					.width(20.dp)
					.padding(end = 5.dp, top = 5.dp)
					.background(Color.Black.copy(alpha = 0.6f), shape = RoundedCornerShape(50))
					.padding(2.dp)
					.clickable {
						onRemove()
					}
			)
		}
		
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDatePicker(
	onDismissRequest: () -> Unit,
	positiveClick: () -> Unit,
	negativeClick: () -> Unit,
	dataState: DatePickerState
) {
	DatePickerDialog(onDismissRequest = onDismissRequest, confirmButton = {
		Button(onClick = positiveClick) {
			Text(text = "ok")
		}
	}, dismissButton = {
		Button(onClick = negativeClick) {
			Text(text = "Cancel")
		}
	}) {
		DatePicker(state = dataState, showModeToggle = true)
		
	}
	
}
