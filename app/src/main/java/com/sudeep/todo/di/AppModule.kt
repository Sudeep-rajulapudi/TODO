package com.sudeep.todo.di

import android.app.Application
import androidx.room.Room
import com.sudeep.todo.feature_todo.data.data_source.TodoDatabase
import com.sudeep.todo.feature_todo.data.repository.TodoRepositoryImpl
import com.sudeep.todo.feature_todo.domain.repository.TodoRepository
import com.sudeep.todo.feature_todo.domain.use_case.AddTodo
import com.sudeep.todo.feature_todo.domain.use_case.FindTodo
import com.sudeep.todo.feature_todo.domain.use_case.GetTodos
import com.sudeep.todo.feature_todo.domain.use_case.TodoUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesTodoDatabase(app:Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            TodoDatabase.DATABASE_NAME
        ).build()

    }
    @Provides
    @Singleton
    fun provideTaskRepository(db:TodoDatabase):TodoRepository{
        return TodoRepositoryImpl(db.todoDao)
    }

    @Provides
    @Singleton
    fun provideTaskUseCase(repository: TodoRepository):TodoUseCases{
        return TodoUseCases(
            addTodo = AddTodo(repository),
            findTodo = FindTodo(repository),
            getTodos = GetTodos(repository)
        )
    }

}