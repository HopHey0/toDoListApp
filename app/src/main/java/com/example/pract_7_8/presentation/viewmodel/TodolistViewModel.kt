package com.example.pract_7_8.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.domain.usecase.GetTodosUseCase
import com.example.pract_7_8.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodolistViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase
) : ViewModel() {

    var todos by mutableStateOf<List<TodoItem>>(emptyList())

    init {
        loadTodos()
    }

    fun loadTodos(){
        viewModelScope.launch {
            todos = getTodosUseCase.invoke()
        }
    }

    fun onToggleTodo(id: Int) {
        viewModelScope.launch {
            toggleTodoUseCase.invoke(id)
            loadTodos()
        }
    }
}

