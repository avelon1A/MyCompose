package com.bosch.composewithkotlin20.presentaion.ui.todo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodoViewModel  : ViewModel() {
	
	private val _todoUiState = MutableStateFlow(HomeUiState())
	val todoUiState: StateFlow<HomeUiState> = _todoUiState.asStateFlow()

	fun todoUiEvent(event: TodoUIEvent) {
		when(event){
			is TodoUIEvent.AddTask -> addTask(event)
			TodoUIEvent.CloseTaskDialog -> closeTaskDialog()
			is TodoUIEvent.CompletedTask -> completeTask(event)
			is TodoUIEvent.OpenTaskDialog -> openTaskDialog(event)
			TodoUIEvent.ToggleDialog -> toggleDialog()
		}
	}
	
	private fun closeTaskDialog() {
		_todoUiState.value = _todoUiState.value.copy(
			showDialog = false,
			openTaskDialog = false
		)
	}
	
	private fun toggleDialog() {
		_todoUiState.value = _todoUiState.value.copy(
			showDialog = !_todoUiState.value.showDialog
		
		)
	}
	
	private fun openTaskDialog(event: TodoUIEvent.OpenTaskDialog) {
		val taskItem = event.task
		_todoUiState.value = _todoUiState.value.copy(
			openTaskDialog = true,
			selectedTask = taskItem
			
		)
		

		
	}
	
	private fun completeTask(event: TodoUIEvent.CompletedTask) {
		val taskItem = event.taskIndex
		val taskList = _todoUiState.value.tasksList.toMutableList()
		val completedTaskList = _todoUiState.value.completedTasks.toMutableList()
		
		
		val task = taskList[taskItem]
		completedTaskList.add(task)
		taskList.removeAt(taskItem)
		_todoUiState.value = _todoUiState.value.copy(
			tasksList =taskList,
			completedTasks = completedTaskList
		)
		
	
	}
	
	private fun addTask(event: TodoUIEvent.AddTask) {
		val task = event.task
		val taskList = _todoUiState.value.tasksList.toMutableList()
		
		taskList.add(task)
		_todoUiState.value = _todoUiState.value.copy(
			tasksList =taskList,

		)
		
	}
	
}

