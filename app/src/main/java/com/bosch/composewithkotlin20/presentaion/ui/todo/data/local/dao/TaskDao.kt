package com.bosch.composewithkotlin20.presentaion.ui.todo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bosch.composewithkotlin20.presentaion.ui.todo.data.local.database.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Insert
    suspend fun insertTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Query("DELETE FROM tasks WHERE taskId = :id")
    suspend fun deleteTask(id: Long)

    @Query("SELECT * FROM tasks WHERE taskId = :id")
    suspend fun getTaskById(id: Long): TaskEntity?

    @Query("UPDATE tasks SET completed = :isCompleted WHERE taskId = :id")
    suspend fun updateTaskCompletedStatus(id: Long, isCompleted: Boolean)
}
