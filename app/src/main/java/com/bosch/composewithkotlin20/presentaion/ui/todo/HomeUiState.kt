package com.bosch.composewithkotlin20.presentaion.ui.todo

import com.bosch.composewithkotlin20.presentaion.ui.todo.data.local.database.TaskEntity

data class HomeUiState(
	val loading: Boolean = false,
	val showDialog: Boolean = false,
	val openTaskDialog: Boolean = false,
	val selectedTask: Tasks? = null,
	val tasksList: List<Tasks> = emptyList(),
	val completedTasks: List<Tasks> = emptyList(),
	val showError: Boolean = false,
	val errorMessage: String = ""
)

sealed interface TodoUIEvent {
	data object ToggleDialog : TodoUIEvent
	data class AddTask(val task: Tasks) : TodoUIEvent
	data class CompletedTask(val taskId: Int) : TodoUIEvent
	data class OpenTaskDialog(val task: Tasks) : TodoUIEvent
	data object CloseTaskDialog : TodoUIEvent
}


data class Tasks(
	val taskId: Long,
	val taskName: String,
	val taskDetails: String,
	val taskEndDate: String,
	val taskFiles: List<String> = emptyList(),
	val completed: Boolean = false
) {
	val toEntity: TaskEntity
		get() = TaskEntity(
			taskId = this.taskId,
			taskName = this.taskName,
			taskDetails = this.taskDetails,
			taskEndDate = this.taskEndDate,
			taskFiles = this.taskFiles.joinToString(","),
			completed = this.completed
		)
}
