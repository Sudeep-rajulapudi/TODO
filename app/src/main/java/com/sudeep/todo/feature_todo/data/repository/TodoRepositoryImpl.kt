package com.sudeep.todo.feature_todo.data.repository

import com.sudeep.todo.feature_todo.data.data_source.TodoDao
import com.sudeep.todo.feature_todo.domain.model.Todo
import com.sudeep.todo.feature_todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val todoDao: TodoDao
): TodoRepository {
    override fun getTodos(): Flow<List<Todo>> {
        return todoDao.getTodos()
    }

    override suspend fun findTodos(query: String): Flow<List<Todo>> {
        return todoDao.findTodos(query)
    }

    override suspend fun insertTodo(todo: Todo) {
        return todoDao.insertTodo(todo)
    }
}