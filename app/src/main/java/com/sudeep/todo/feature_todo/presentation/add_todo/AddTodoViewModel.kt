package com.sudeep.todo.feature_todo.presentation.add_todo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sudeep.todo.feature_todo.domain.model.Todo
import com.sudeep.todo.feature_todo.domain.use_case.TodoUseCases
import com.sudeep.todo.feature_todo.domain.util.ErrorTodoException
import com.sudeep.todo.feature_todo.domain.util.InvalidTodoException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _todoTitle = mutableStateOf(TodoTextFieldState(hint = "Enter Todo title"))
    val todoTitle: State<TodoTextFieldState> = _todoTitle
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun onEvent(event:AddTodoEvent){
        when(event){
            is AddTodoEvent.EnteredTitle -> {
                _todoTitle.value = todoTitle.value.copy(
                    text = event.value
                )
            }
            is AddTodoEvent.ChangeTitleFocus -> {
                _todoTitle.value = _todoTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && _todoTitle.value.text.isBlank()
                )
            }
            is AddTodoEvent.saveTodo -> {
                viewModelScope.launch {
                    try {
                        _isLoading.value = true
                        delay(3000)
                        todoUseCases.addTodo(
                            Todo(
                                title = todoTitle.value.text.trim()
                            )
                        )
                        todoUseCases.getTodos.invoke()
                        _eventFlow.emit(UiEvent.saveTodo)
                    }
                    catch (e: InvalidTodoException){
                        _error.value = e.message?:"Todo title can't be empty"
                    }catch (e: ErrorTodoException){

                        _error.value = e.message?:"Unable to save Todo"
                    }
                    finally {
                        _isLoading.value = false
                    }
                }
            }
        }
    }
    fun clearError(){
        _error.value = null
    }

    sealed class UiEvent{
        data class showSnackbar(val message:String):UiEvent()
        object saveTodo:UiEvent()
    }
}