package com.example.pract_7_8.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoItemEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todo_id")
    var id: Int = 0,
    var title: String,
    var description: String,
    var isCompleted: Boolean
)
