package com.sudeep.todo.feature_todo.presentation.todos.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sudeep.todo.feature_todo.domain.model.Todo

@Composable
fun TodoItem(
    todo: Todo,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier){

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 32.dp, top = 8.dp, bottom = 8.dp)
        ) {
            Text(
                text = todo.title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )


        }

    }

}