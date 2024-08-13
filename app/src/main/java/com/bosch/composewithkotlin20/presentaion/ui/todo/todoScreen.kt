package com.bosch.composewithkotlin20.presentaion.ui.todo

import TodoViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bosch.composewithkotlin20.R
import com.bosch.composewithkotlin20.presentaion.ui.todo.common.AddTaskDialog
import com.bosch.composewithkotlin20.presentaion.ui.todo.common.TaskItem
import com.bosch.composewithkotlin20.presentaion.ui.todo.common.TaskItemDone
import com.bosch.composewithkotlin20.presentaion.ui.todo.common.ViewTask
import kotlinx.serialization.Serializable

@Composable
fun TodoScreen(viewModel: TodoViewModel) {
	
	val uiState by viewModel.todoUiState.collectAsState()
	
	ConstraintLayout(
		modifier = Modifier
			.fillMaxSize()
			.background(Color.White)
	) {
		val (toolbar, logoBox, myTaskText, myTaskLazyColumn, CompletedTaskText, CompletedTaskLazyColumn, addFab) = createRefs()
		
		Box(modifier = Modifier
			.fillMaxWidth()
			.height(70.dp)
			.background(Color.LightGray)
			.constrainAs(toolbar) {
				top.linkTo(parent.top)
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				width = Dimension.fillToConstraints
			}
		) {
			Image(
				painter = painterResource(id = R.drawable.person),
				contentDescription = "user profile picture",
				modifier = Modifier.fillMaxSize(),
				Alignment.CenterEnd
			)
		}
		Box(modifier = Modifier
			.height(130.dp)
			.width(130.dp)
			.padding(top = 15.dp)
			.background(Color.Transparent)
			.constrainAs(logoBox) {
			}
		)
		{
			Image(
				painter = painterResource(id = R.drawable.area_chart_svgrepo_com), contentDescription = "icon",
				modifier = Modifier
					.fillMaxSize()
					.padding(10.dp)
			)
		}
		
		Text(text = "My Tasks", fontSize = 21.sp, modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 20.dp)
			.constrainAs(myTaskText) {
				top.linkTo(logoBox.bottom)
			}
		)
		LazyColumn(modifier = Modifier
			.fillMaxSize()
			.constrainAs(myTaskLazyColumn) {
				top.linkTo(myTaskText.bottom)
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				bottom.linkTo(CompletedTaskText.top)
				height = Dimension.fillToConstraints
			}) {
			items(uiState.tasksList.size) { index ->
				TaskItem(task = uiState.tasksList[index], onTaskCompleted = { checked ->
					if (checked) {
						viewModel.todoUiEvent(event = TodoUIEvent.CompletedTask(index))
					}
				}, onClick = {
					viewModel.todoUiEvent(event = TodoUIEvent.OpenTaskDialog(uiState.tasksList[index]))
				}
				)
				
			}
			
		}
		
		Text(text = "Completed Tasks", fontSize = 21.sp, modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 20.dp)
			.constrainAs(CompletedTaskText) {
				top.linkTo(myTaskLazyColumn.bottom)
				bottom.linkTo(CompletedTaskLazyColumn.top)
			}
		)
		LazyColumn(modifier = Modifier
			.fillMaxSize()
			.constrainAs(CompletedTaskLazyColumn) {
				top.linkTo(CompletedTaskText.bottom)
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				bottom.linkTo(parent.bottom)
				height = Dimension.fillToConstraints
			}) {
			items(uiState.completedTasks.size) { index ->
				TaskItemDone(task = uiState.completedTasks[index])
			}
			
		}
		FloatingActionButton(
			modifier = Modifier
				.padding(24.dp)
				.constrainAs(addFab) {
					bottom.linkTo(parent.bottom)
					end.linkTo(parent.end)
				},
			containerColor = MaterialTheme.colorScheme.primary,
			onClick = { viewModel.todoUiEvent(event = TodoUIEvent.ToggleDialog)},
			shape = CircleShape
		) {
			Icon(Icons.Filled.Add, "Floating action button.")
		}
		
		if (uiState.showDialog) {
			Box(
				modifier = Modifier
					.fillMaxSize()
					.background(color = Color.Black.copy(alpha = 0.2f))
			)
			{
				AddTaskDialog(onDismiss = {
					viewModel.todoUiEvent(TodoUIEvent.ToggleDialog)
				}, onSave = {
					viewModel.todoUiEvent(TodoUIEvent.AddTask(it))
					viewModel.todoUiEvent(TodoUIEvent.ToggleDialog)
				
				})
			}
		}
			
			if (uiState.openTaskDialog) {
				
				uiState.selectedTask?.let { item ->
					ViewTask(tasks = item, onDismiss = {
						viewModel.todoUiEvent(TodoUIEvent.CloseTaskDialog)
					}, onComplete = {
						
						val index = uiState.tasksList.indexOf(item)
						viewModel.todoUiEvent(TodoUIEvent.CompletedTask(index))
						viewModel.todoUiEvent(TodoUIEvent.CloseTaskDialog)
						
					})
				}
			}
			if (uiState.loading) {
			
			}
			if (uiState.showError) {
			
			}
		}
		
	}


@Serializable
object TodoScreen
	
	
	@Preview
	@Composable
	fun TodoScreenPreview() {

	}
	
	@Preview
	@Composable
	fun TaskItemPreview() {

	}