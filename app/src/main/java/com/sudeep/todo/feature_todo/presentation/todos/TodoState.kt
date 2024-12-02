package com.sudeep.todo.feature_todo.presentation.todos

import com.sudeep.todo.feature_todo.domain.model.Todo

data class TodoState(
    val todos:List<Todo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
