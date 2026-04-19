package com.example.pract_7_8.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.domain.usecase.AddTodoUseCase
import com.example.pract_7_8.domain.usecase.DeleteTodoUseCase
import com.example.pract_7_8.domain.usecase.GetTodosUseCase
import com.example.pract_7_8.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.Job
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

class TodoListViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
) : ViewModel() {

    private val _listUiState = MutableStateFlow(TodoUiState())
    val listUiState = _listUiState.asStateFlow()

    private var listJob: Job? = null

    init {
        listJob = viewModelScope.launch {
            getTodosUseCase.invoke().collect { todoList ->
                _listUiState.update { it.copy(todos = todoList) }
            }
        }
    }

    fun onShowDialogClick() {
        _listUiState.update { it.copy(
            todoDialogHeader = "",
            todoDialogBody = "",
            showDialog = true
        )}
    }

    fun onDialogDismiss() {
        _listUiState.update { it.copy(showDialog = false) }
    }

    fun onHeaderChange(newValue: String) {
        if (newValue.length <= 40) {
            _listUiState.update { it.copy(todoDialogHeader = newValue) }
        }
    }

    fun onBodyChange(newValue: String) {
        if (newValue.length <= 100) {
            _listUiState.update { it.copy(todoDialogBody = newValue) }
        }
    }

    fun onToggleTodo(id: Int) {
        viewModelScope.launch { toggleTodoUseCase.invoke(id) }
    }

    fun addTodo(header: String, body: String) {
        viewModelScope.launch { addTodoUseCase.invoke(header, body) }
    }

    fun deleteTodo(id: Int) {
        viewModelScope.launch { deleteTodoUseCase.invoke(id) }
    }

    override fun onCleared() {
        super.onCleared()
        listJob = null
    }
}