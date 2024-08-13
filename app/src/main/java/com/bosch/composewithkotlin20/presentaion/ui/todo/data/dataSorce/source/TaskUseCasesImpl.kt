package com.bosch.composewithkotlin20.presentaion.ui.todo.data.dataSorce.source

import com.bosch.composewithkotlin20.presentaion.ui.todo.data.local.database.TaskEntity
import com.bosch.composewithkotlin20.presentaion.ui.todo.domain.repo.TaskRepository
import com.bosch.composewithkotlin20.presentaion.ui.todo.domain.usecase.TaskUseCases
import kotlinx.coroutines.flow.Flow

class TaskUseCasesImpl(private val taskRepository: TaskRepository) : TaskUseCases {

    override suspend fun addTask(task: TaskEntity) {
        taskRepository.addTask(task)
    }

    override suspend fun completeTask(taskId: Long) {
        taskRepository.completeTask(taskId)
    }

    override fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskRepository.getAllTasks()
    }

    override suspend fun deleteTask(taskId: Long) {
        taskRepository.deleteTask(taskId)
    }
}
