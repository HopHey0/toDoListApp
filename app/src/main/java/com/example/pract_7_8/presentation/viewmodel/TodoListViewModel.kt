package com.example.pract_7_8.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.domain.usecase.preferencesUseCases.GetCompletedColorUseCase
import com.example.pract_7_8.domain.usecase.preferencesUseCases.SetCompletedColorUseCase
import com.example.pract_7_8.domain.usecase.todoUseCases.AddTodoUseCase
import com.example.pract_7_8.domain.usecase.todoUseCases.DeleteTodoUseCase
import com.example.pract_7_8.domain.usecase.todoUseCases.GetTodosUseCase
import com.example.pract_7_8.domain.usecase.todoUseCases.ToggleTodoUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TodoUiState(
    var todos: List<TodoItem> = emptyList(),
    var showDialog: Boolean = false,
    var todoDialogHeader: String = "",
    var todoDialogBody: String = "",
    var isCompletedHasColor: Boolean = false
)

class TodoListViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val getCompletedColorUseCase: GetCompletedColorUseCase,
    private val setCompletedColorUseCase: SetCompletedColorUseCase
) : ViewModel() {

    private val _listUiState = MutableStateFlow(TodoUiState())
    val listUiState = _listUiState.asStateFlow()

    private var listJob: Job? = null

    init {
        viewModelScope.launch {
            getTodosUseCase.invoke().collect { todoList ->
                _listUiState.update { it.copy(todos = todoList) }
            }
        }

        viewModelScope.launch {
            getCompletedColorUseCase.invoke().collect { isColored ->
                _listUiState.update { it.copy(isCompletedHasColor = isColored) }
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

    fun toggleCompletedColor() {
        viewModelScope.launch {
            val current = _listUiState.value.isCompletedHasColor
            setCompletedColorUseCase.invoke(!current)
        }
    }

    override fun onCleared() {
        super.onCleared()
        listJob = null
    }
}