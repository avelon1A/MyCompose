package com.bosch.composewithkotlin20.presentaion.ui.todo

sealed interface TodoUIEvent {
    data object ToggleDialog : TodoUIEvent
    data class AddTask(val task: Tasks) : TodoUIEvent
    data class CompletedTask(val taskId: Int) : TodoUIEvent
    data class OpenTaskDialog(val task: Tasks) : TodoUIEvent
    data object CloseTaskDialog : TodoUIEvent
}
