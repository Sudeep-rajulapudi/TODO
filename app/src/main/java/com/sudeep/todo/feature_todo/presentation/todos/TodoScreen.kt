package com.sudeep.todo.feature_todo.presentation.todos

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sudeep.todo.feature_todo.presentation.todos.components.TodoItem
import com.sudeep.todo.feature_todo.presentation.util.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodoScreen(
    navController: NavController,
    viewModel: TodosViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val searchQuery = remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddTodoScreen.route)
            }, containerColor = MaterialTheme.colorScheme.primary) { Icon(imageVector = Icons.Default.Add, contentDescription = "Add todo") }
        },
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery.value,
                onValueChange = {
                    searchQuery.value = it
                    viewModel.findTodos(it)
                },
                label = { Text("Search TODOs") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (state.todos.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Press the + button to add a TODO item")
                }
            } else {

            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.todos){ todo ->
                    TodoItem(todo = todo,
                        modifier = Modifier
                            .fillMaxWidth())
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

}