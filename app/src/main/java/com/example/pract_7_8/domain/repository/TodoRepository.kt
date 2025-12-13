package com.example.pract_7_8.domain.repository

import com.example.pract_7_8.domain.model.TodoItem

interface TodoRepository {
    suspend fun getTodos(): List<TodoItem>
    suspend fun toggleTodo(id: Int)
}
