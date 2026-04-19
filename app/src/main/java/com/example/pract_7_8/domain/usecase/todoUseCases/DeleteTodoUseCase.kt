package com.example.pract_7_8.domain.usecase.todoUseCases

import com.example.pract_7_8.domain.repository.TodoRepository

class DeleteTodoUseCase(private val repository: TodoRepository) {
    suspend fun invoke(id: Int) = repository.deleteTodo(id)
}