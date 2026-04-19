package com.example.pract_7_8.domain.usecase

import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetSingleTodoUseCase(private val repository: TodoRepository) {
    fun invoke(id: Int): Flow<TodoItem> {
        return repository.getTodo(id)
    }
}