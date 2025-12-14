package com.example.pract_7_8.presentation.viewmodel

import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.domain.usecase.AddTodoUseCase
import com.example.pract_7_8.domain.usecase.DeleteTodoUseCase
import com.example.pract_7_8.domain.usecase.GetTodosUseCase
import com.example.pract_7_8.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.launch

class TodolistViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel() {

    val todos = getTodosUseCase.invoke().asLiveData()

    fun onToggleTodo(id: Int) {
        viewModelScope.launch {
            toggleTodoUseCase.invoke(id)
        }
    }

    fun addTodo(item: TodoItem) {
        viewModelScope.launch {
            addTodoUseCase.invoke(item)
        }
    }

    fun deleteTodo(id: Int){
        viewModelScope.launch {
            deleteTodoUseCase.invoke(id)
        }
    }
}

