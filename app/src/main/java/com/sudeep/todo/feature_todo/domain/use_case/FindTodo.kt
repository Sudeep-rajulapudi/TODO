package com.sudeep.todo.feature_todo.domain.use_case

import com.sudeep.todo.feature_todo.domain.model.Todo
import com.sudeep.todo.feature_todo.domain.repository.TodoRepository
import com.sudeep.todo.feature_todo.domain.util.InvalidTodoException
import kotlinx.coroutines.flow.Flow

class FindTodo(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(query: String):Flow<List<Todo>>{
        if(query.isNullOrEmpty()){
            throw InvalidTodoException("Todo title can't be empty")
        }
        return repository.findTodos(query)

    }
}