package com.sudeep.todo.feature_todo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val title:String
)
