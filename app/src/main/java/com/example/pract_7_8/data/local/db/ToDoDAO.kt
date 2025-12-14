package com.example.pract_7_8.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.pract_7_8.data.local.entity.TodoItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Tododao {
    @Query("SELECT * FROM todos")
    fun getTodos(): Flow<List<TodoItemEntity>>

    @Insert
    fun addTodo(todo: TodoItemEntity)

    @Query("UPDATE todos SET isCompleted = NOT isCompleted WHERE todos.todo_id = :id")
    fun toggleTodo(id: Int)

    @Delete
    fun deleteTodo(id: Int)
}