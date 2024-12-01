package com.sudeep.todo.feature_todo.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sudeep.todo.feature_todo.domain.model.Todo

@Database(
    entities = [Todo::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDatabase:RoomDatabase() {
    abstract val todoDao:TodoDao
    companion object{
        val DATABASE_NAME = "todo_db"
    }
}