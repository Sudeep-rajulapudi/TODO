package com.sudeep.todo.feature_todo.presentation.util

sealed class Screen(val route:String) {
    object TodoScreen : Screen("todos_screen")
    object AddTodoScreen : Screen("add_todo_screen")

}