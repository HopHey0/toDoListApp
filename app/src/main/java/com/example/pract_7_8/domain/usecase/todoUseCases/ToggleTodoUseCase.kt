package com.example.pract_7_8.domain.usecase.todoUseCases

import com.example.pract_7_8.domain.repository.TodoRepository

class ToggleTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(id: Int): Unit = repository.toggleTodo(id)
}