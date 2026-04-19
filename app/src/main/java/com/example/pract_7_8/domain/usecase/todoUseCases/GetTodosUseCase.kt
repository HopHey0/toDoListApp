package com.example.pract_7_8.domain.usecase.todoUseCases

import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetTodosUseCase(private val repository: TodoRepository) {
    fun invoke(): Flow<List<TodoItem>> = repository.getTodos()
}
