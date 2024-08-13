package com.bosch.composewithkotlin20.presentaion.ui.todo.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bosch.composewithkotlin20.presentaion.ui.todo.Tasks

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val taskId: Long = 0,
    val taskName: String,
    val taskDetails: String,
    val taskEndDate: String,
    val taskFiles: String
) {
    fun toDomain(): Tasks {
        return Tasks(
            taskId = this.taskId,
            taskName = this.taskName,
            taskDetails = this.taskDetails,
            taskEndDate = this.taskEndDate,
            taskFiles = this.taskFiles.split(",")
        )
    }
}