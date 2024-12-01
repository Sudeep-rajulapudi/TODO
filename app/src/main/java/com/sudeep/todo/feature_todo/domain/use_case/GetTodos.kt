package com.sudeep.todo.feature_todo.domain.use_case

import com.sudeep.todo.feature_todo.domain.model.Todo
import com.sudeep.todo.feature_todo.domain.repository.TodoRepository
import com.sudeep.todo.feature_todo.domain.util.InvalidTodoException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTodos(
    private val repository: TodoRepository
) {
    operator fun invoke():Flow<List<Todo>>{

        return repository.getTodos()

    }
}