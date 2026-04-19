package com.example.pract_7_8.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pract_7_8.data.local.entity.TodoItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Tododao {
    @Query("SELECT * FROM todos")
    fun getTodos(): Flow<List<TodoItemEntity>>

    @Insert
    suspend fun addTodo(todo: TodoItemEntity)

    @Query("UPDATE todos SET isCompleted = NOT isCompleted WHERE todos.todo_id = :id")
    suspend fun toggleTodo(id: Int)

    @Query("DELETE FROM todos WHERE todos.todo_id = :id")
    suspend fun deleteTodo(id: Int)

    @Insert
    suspend fun insertAll(listItem: List<TodoItemEntity>)

    @Query("SELECT * FROM todos WHERE todos.todo_id = :id")
    fun getTodo(id: Int): Flow<TodoItemEntity>

    @Query("UPDATE todos SET title = :newTitle, description = :newDescription WHERE todos.todo_id = :id")
    suspend fun updateTodo(id: Int, newTitle: String, newDescription: String)
}