package com.bosch.composewithkotlin20.presentaion.ui.todo.data.dataSorce.repoImp

import com.bosch.composewithkotlin20.presentaion.ui.todo.data.local.dao.TaskDao
import com.bosch.composewithkotlin20.presentaion.ui.todo.data.local.database.TaskEntity
import com.bosch.composewithkotlin20.presentaion.ui.todo.domain.repo.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {

    override suspend fun addTask(task: TaskEntity) {
        taskDao.insertTask(task)
    }

    override suspend fun completeTask(taskId: Long) {
        val task = taskDao.getTaskById(taskId) ?: return
        taskDao.updateTask(task.copy(/* update fields as needed */))
    }

    override fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskDao.getAllTasks()
    }

    override suspend fun deleteTask(taskId: Long) {
        taskDao.deleteTask(taskId)
    }

    override suspend fun updateTaskCompletedStatus(id: Long, isCompleted: Boolean) {
        taskDao.updateTaskCompletedStatus(id, isCompleted)
    }
}
