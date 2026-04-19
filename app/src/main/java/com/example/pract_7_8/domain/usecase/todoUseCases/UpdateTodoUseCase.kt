package com.example.pract_7_8.domain.usecase.todoUseCases

import com.example.pract_7_8.domain.repository.TodoRepository

class UpdateTodoUseCase(
    private val repository: TodoRepository
) {
    suspend fun invoke(id: Int, newTitle: String, newDescription: String){
        repository.updateTodo(id, newTitle, newDescription)
    }
}