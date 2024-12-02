package com.sudeep.todo.feature_todo.data.data_source

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sudeep.todo.feature_todo.domain.model.Todo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
@RunWith(AndroidJUnit4::class)
class TodoDatabaseTest {

    private lateinit var database: TodoDatabase
    private lateinit var todoDao: TodoDao

    @Before
    fun setup() {

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TodoDatabase::class.java
        ).allowMainThreadQueries() // Allow main thread queries for testing
            .build()

        todoDao = database.todoDao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertTodo_insertsItemCorrectly() = runBlocking {

        val todo = Todo(id = 1, title = "Test TODO")

        todoDao.insertTodo(todo)

        val allTodos = todoDao.getTodos().first()
        assertThat(allTodos).contains(todo)
    }


    @Test
    fun searchTodo_returnsFilteredResults() = runBlocking {
        val todos = listOf(
            Todo(id = 1, title = "Buy groceries"),
            Todo(id = 2, title = "Call mom"),
            Todo(id = 3, title = "Clean house")
        )
        todos.forEach { todoDao.insertTodo(it) }

        val result = todoDao.findTodos("Call").first()

        assertThat(result).containsExactly(todos[1])
    }

    @Test
    fun insertTodo_handlesPrimaryKeyConflict() = runBlocking {

        val todo1 = Todo(id = 1, title = "Original TODO")
        val todo2 = Todo(id = 1, title = "Updated TODO")

        todoDao.insertTodo(todo1)
        todoDao.insertTodo(todo2)

        val allTodos = todoDao.getTodos().first()
        assertThat(allTodos.size).isEqualTo(1)
        assertThat(allTodos[0].title).isEqualTo("Updated TODO")
    }
}