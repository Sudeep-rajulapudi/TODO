package com.sudeep.todo.feature_todo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Todo(
    @PrimaryKey
    val id:Int? = 0,
    val title:String
)
