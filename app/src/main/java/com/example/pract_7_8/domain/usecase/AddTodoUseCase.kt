package com.example.pract_7_8.domain.usecase

import com.example.pract_7_8.domain.repository.TodoRepository

class AddTodoUseCase(private val repository: TodoRepository) {
    suspend fun invoke(title: String, description: String) = repository.addTodo(
        title = title,
        description = description
    )
}