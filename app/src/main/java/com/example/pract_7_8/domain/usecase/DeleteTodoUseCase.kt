package com.example.pract_7_8.domain.usecase

import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.domain.repository.TodoRepository

class DeleteTodoUseCase(private val repository: TodoRepository) {
    suspend fun invoke(id: Int) = repository.deleteTodo(id)
}