package com.sudeep.todo.feature_todo.presentation.add_todo

data class TodoTextFieldState(
    val text:String = "",
    val hint:String = "",
    val isHintVisible:Boolean = true
)
