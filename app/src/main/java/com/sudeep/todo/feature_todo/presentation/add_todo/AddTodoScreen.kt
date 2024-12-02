package com.sudeep.todo.feature_todo.presentation.add_todo

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sudeep.todo.feature_todo.presentation.add_todo.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddTodoScreen(
    navController: NavController,
    viewModel: AddTodoViewModel = hiltViewModel()
){
    val titleState = viewModel.todoTitle.value
    val error by viewModel.error.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()


    val snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is AddTodoViewModel.UiEvent.showSnackbar ->{
                    snackbarHostState.showSnackbar(message = event.message, duration = SnackbarDuration.Short)
                }
                is AddTodoViewModel.UiEvent.saveTodo ->{
                    navController.navigateUp()
                }
            }
        }
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp))
        {

            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                isHintVisible = titleState.isHintVisible,
                modifier = Modifier,
                onValueChange ={
                    viewModel.onEvent(AddTodoEvent.EnteredTitle(it))
                } ,
                onFocusChange = {
                    viewModel.onEvent(AddTodoEvent.ChangeTitleFocus(it))
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = {
                viewModel.onEvent(AddTodoEvent.saveTodo)

            }) {
                Text(modifier = Modifier.fillMaxWidth().padding(8.dp), text = "Save Todo", textAlign = TextAlign.Center, fontSize = 18.sp)
            }

        }
        if (error != null) {
            AlertDialog(
                onDismissRequest = { viewModel.clearError() },
                confirmButton = {
                    Button(onClick = { viewModel.clearError() }) {
                        Text("Dismiss")
                    }
                },
                text = { Text(error ?: "Unknown error") }
            )
        }
    }





}

