package com.example.pract_7_8.domain.usecase

import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.domain.repository.TodoRepository

class ToggleTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(id: Int): Unit = repository.toggleTodo(id)
}