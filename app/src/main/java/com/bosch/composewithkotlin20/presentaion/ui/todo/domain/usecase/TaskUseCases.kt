package com.bosch.composewithkotlin20.presentaion.ui.todo.domain.usecase

import com.bosch.composewithkotlin20.presentaion.ui.todo.data.local.database.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TaskUseCases {
    suspend fun addTask(task: TaskEntity)
    suspend fun completeTask(taskId: Long)
    fun getAllTasks(): Flow<List<TaskEntity>>
    suspend fun deleteTask(taskId: Long)
}
