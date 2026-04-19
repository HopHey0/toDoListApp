package com.example.pract_7_8.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.domain.usecase.GetSingleTodoUseCase
import com.example.pract_7_8.domain.usecase.UpdateTodoUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TodoDetailsUiState(
    val item: TodoItem? = null,
    val showDialog: Boolean = false,
    val todoDialogHeader: String = "",
    val todoDialogBody: String = ""
)

class TodoDetailsViewModel(
    private val getSingleTodoUseCase: GetSingleTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase
) : ViewModel() {

    private val _detailsUiState = MutableStateFlow(TodoDetailsUiState())
    val detailsUiState = _detailsUiState.asStateFlow()

    private var detailsJob: Job? = null

    fun loadTodo(id: Int) {
        detailsJob?.cancel()
        detailsJob = viewModelScope.launch {
            getSingleTodoUseCase.invoke(id).collect { todo ->
                _detailsUiState.update { it.copy(item = todo) }
            }
        }
    }

    fun onShowEditDialogClick() {
        val item = _detailsUiState.value.item ?: return
        _detailsUiState.update { it.copy(
            showDialog = true,
            todoDialogHeader = item.title,
            todoDialogBody = item.description
        )}
    }

    fun onDialogDismiss() {
        _detailsUiState.update { it.copy(showDialog = false) }
    }

    fun onHeaderChange(newValue: String) {
        if (newValue.length <= 40) {
            _detailsUiState.update { it.copy(todoDialogHeader = newValue) }
        }
    }

    fun onBodyChange(newValue: String) {
        if (newValue.length <= 100) {
            _detailsUiState.update { it.copy(todoDialogBody = newValue) }
        }
    }

    fun updateTodo(newHeader: String, newBody: String) {
        val id = _detailsUiState.value.item?.id ?: return
        viewModelScope.launch { updateTodoUseCase.invoke(id, newHeader, newBody) }
    }

    override fun onCleared() {
        super.onCleared()
        detailsJob = null
    }
}