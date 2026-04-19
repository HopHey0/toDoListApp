package com.example.pract_7_8.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.domain.usecase.AddTodoUseCase
import com.example.pract_7_8.domain.usecase.DeleteTodoUseCase
import com.example.pract_7_8.domain.usecase.GetSingleTodoUseCase
import com.example.pract_7_8.domain.usecase.GetTodosUseCase
import com.example.pract_7_8.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TodoUiState(
    var todos: List<TodoItem> = emptyList(),
    var showDialog: Boolean = false,
    var todoDialogHeader: String = "",
    var todoDialogBody: String = ""
)

data class TodoDetailsUiState(
    var item: TodoItem? = null
)

class TodolistViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val getSingleTodoUseCase: GetSingleTodoUseCase
) : ViewModel() {

    private val _listUiState = MutableStateFlow<TodoUiState>(TodoUiState())
    val listUiState = _listUiState.asStateFlow()
    
    private val _detailsUiState = MutableStateFlow<TodoDetailsUiState>(TodoDetailsUiState())
    val detailsUiState = _detailsUiState.asStateFlow()
    private var todoJob: Job? = null

    init {
        todoJob?.cancel()
        todoJob = viewModelScope.launch {
            getTodosUseCase.invoke().collect { todoList ->
                _listUiState.update { uiState ->
                    uiState.copy(
                        todos = todoList
                    )
                }
            }
        }
    }

    fun onShowDialogClick(){
        _listUiState.update { uiState ->
            uiState.copy(
                showDialog = true
            )
        }
    }

    fun onDialogDismiss(){
        _listUiState.update { uiState ->
            uiState.copy(
                showDialog = false
            )
        }
    }

    fun onHeaderChange(newValue: String){
        if (newValue.length <= 40) {
            _listUiState.update { uiState ->
                uiState.copy(
                    todoDialogHeader = newValue
                )
            }
        }
    }

    fun onBodyChange(newValue: String){
        if (newValue.length <= 100) {
            _listUiState.update { uiState ->
                uiState.copy(
                    todoDialogBody = newValue
                )
            }
        }
    }
    

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
    
    fun getSingleTodo(id: Int){
        todoJob?.cancel()
        todoJob = viewModelScope.launch {
            getSingleTodoUseCase.invoke(id).collect { todo ->
                _detailsUiState.update { uiState ->
                    uiState.copy(
                        item = todo
                    )
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        todoJob = null
    }
}

