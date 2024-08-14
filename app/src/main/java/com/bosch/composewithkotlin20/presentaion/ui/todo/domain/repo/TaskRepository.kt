package com.bosch.composewithkotlin20.presentaion.ui.todo.domain.repo

import com.bosch.composewithkotlin20.presentaion.ui.todo.data.local.database.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun addTask(task: TaskEntity)
    suspend fun completeTask(taskId: Long)
    fun getAllTasks(): Flow<List<TaskEntity>>
    suspend fun deleteTask(taskId: Long)
    suspend fun updateTaskCompletedStatus(id: Long, isCompleted: Boolean)
}
