package com.example.pract_7_8.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.domain.usecase.GetTodosUseCase
import com.example.pract_7_8.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.launch

class TodoViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase
) : ViewModel() {

    var todos by mutableStateOf<List<TodoItem>>(emptyList())


    fun onToggleTodo(id: Int) {
        viewModelScope.launch {
            toggleTodoUseCase(id)
            todos = getTodosUseCase()
        }
    }
}

