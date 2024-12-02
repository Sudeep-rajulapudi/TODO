package com.sudeep.todo.feature_todo.presentation.add_todo

import androidx.compose.ui.focus.FocusState

sealed class AddTodoEvent {
    data class EnteredTitle(val value:String): AddTodoEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddTodoEvent()
    object saveTodo: AddTodoEvent()
}