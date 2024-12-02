package com.sudeep.todo.feature_todo.presentation.todos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sudeep.todo.feature_todo.domain.use_case.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases
): ViewModel() {
    private val _state = mutableStateOf(TodoState())
    val state: State<TodoState> = _state
    init {
        getTodos()
    }
    private fun getTodos() {
        viewModelScope.launch {

            todoUseCases.getTodos.invoke().collect{
                _state.value = state.value.copy(todos = it )
            }
        }

    }

    fun findTodos(query:String){

        viewModelScope.launch {
            if(query.isNotEmpty()){
                todoUseCases.findTodo.invoke(query).collect{
                    _state.value = state.value.copy(todos = it )
                }
            }
            else{
                getTodos()
            }
        }

    }

}

