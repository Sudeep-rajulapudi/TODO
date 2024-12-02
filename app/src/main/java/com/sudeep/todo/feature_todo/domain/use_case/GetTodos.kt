package com.sudeep.todo.feature_todo.domain.use_case

import com.sudeep.todo.feature_todo.domain.model.Todo
import com.sudeep.todo.feature_todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetTodos(
    private val repository: TodoRepository
) {
    operator fun invoke():Flow<List<Todo>>{

        return repository.getTodos()

    }
}