package com.sudeep.todo.feature_todo.domain.use_case

data class TodoUseCases(
    val addTodo: AddTodo,
    val findTodo: FindTodo,
    val getTodos: GetTodos
)
