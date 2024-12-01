package com.sudeep.todo.feature_todo.domain.repository

import com.sudeep.todo.feature_todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodos(): Flow<List<Todo>>

    suspend fun findTodos(query : String): Flow<List<Todo>>

    suspend fun insertTodo(todo : Todo)
}