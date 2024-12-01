package com.sudeep.todo.feature_todo.domain.use_case

import com.sudeep.todo.feature_todo.domain.model.Todo
import com.sudeep.todo.feature_todo.domain.repository.TodoRepository
import com.sudeep.todo.feature_todo.domain.util.ErrorTodoException
import com.sudeep.todo.feature_todo.domain.util.InvalidTodoException

class AddTodo(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo){
        if(todo.title.isNullOrEmpty()){
            throw InvalidTodoException("Todo title can't be empty")
        }
        if(todo.title == "Error"){
            throw ErrorTodoException("Failed to add Todo")
        }
        repository.insertTodo(todo)

    }

}