package com.example.pract_7_8.domain.usecase

import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class AddTodoUseCase(private val repository: TodoRepository) {
    suspend fun invoke(item: TodoItem) = repository.addTodo(item)
}