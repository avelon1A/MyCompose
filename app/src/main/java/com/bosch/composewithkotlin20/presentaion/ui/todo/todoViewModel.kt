package com.bosch.composewithkotlin20.presentaion.ui.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosch.composewithkotlin20.presentaion.ui.todo.domain.usecase.TaskUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val taskUseCases: TaskUseCases) : ViewModel() {

	private val _todoUiState = MutableStateFlow(HomeUiState())
	val todoUiState: StateFlow<HomeUiState> = _todoUiState.asStateFlow()

	init {
		viewModelScope.launch {
			taskUseCases.getAllTasks().collect { taskEntities ->
				val tasks = taskEntities.map { it.toDomain() }
				_todoUiState.value = _todoUiState.value.copy(tasksList = tasks)
			}
		}
	}

	fun todoUiEvent(event: TodoUIEvent) {
		when (event) {
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
		_todoUiState.value = _todoUiState.value.copy(
			openTaskDialog = true,
			selectedTask = event.task
		)
	}

	private fun completeTask(event: TodoUIEvent.CompletedTask) {
		viewModelScope.launch {

			taskUseCases.updateTaskCompletedStatus(event.taskId.toLong() + 1, true)

			_todoUiState.value = _todoUiState.value.copy(
				tasksList = _todoUiState.value.tasksList.map { task ->
					if (task.taskId == event.taskId.toLong()) {
						task.copy(completed = true)
					} else {
						task
					}
				}
			)
		}
	}
	
	private fun addTask(event: TodoUIEvent.AddTask) {
		val task = event.task
		viewModelScope.launch {
			taskUseCases.addTask(task.toEntity)
		}
	}
}
