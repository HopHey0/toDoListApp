package com.example.pract_7_8.domain.repository

import com.example.pract_7_8.domain.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodos(): Flow<List<TodoItem>>
    suspend fun toggleTodo(id: Int)

    suspend fun addTodo(title: String, description: String)

    suspend fun deleteTodo(id: Int)

    fun getTodo(id: Int): Flow<TodoItem>

    suspend fun updateTodo(id: Int, newTitle: String, newDescription: String)
}
