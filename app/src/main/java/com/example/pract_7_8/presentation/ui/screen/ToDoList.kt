package com.example.pract_7_8.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pract_7_8.R
import com.example.pract_7_8.presentation.ui.component.ToDoRow
import com.example.pract_7_8.presentation.ui.component.TodoCreateDialog
import com.example.pract_7_8.presentation.viewmodel.TodoListViewModel

@Composable
fun ToDoList(
    todolistViewModel: TodoListViewModel,
    onItemClick: (Int) -> Unit,
    modifier: Modifier
){
    val listUiState = todolistViewModel.listUiState.collectAsStateWithLifecycle().value
    Column() {
        TopBarToDoList(
            listUiState.showDialog,
            listUiState.todoDialogHeader,
            listUiState.todoDialogBody,
            todolistViewModel::addTodo,
            todolistViewModel::onShowDialogClick,
            todolistViewModel::onDialogDismiss,
            todolistViewModel::onHeaderChange,
            todolistViewModel::onBodyChange
        )
        LazyColumn(
            modifier = modifier
                .testTag("homeScreen")
                .padding(horizontal = 15.dp),
        ) {
            items(listUiState.todos) { item ->
                ToDoRow(
                    item,
                    onClick = { onItemClick(item.id) },
                    onChecked = { todolistViewModel.onToggleTodo(item.id) },
                    todolistViewModel
                )
                Spacer(modifier = Modifier.padding(vertical = 5.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarToDoList(
    showDialog: Boolean,
    todoDialogHeader: String,
    todoDialogBody: String,
    addTodo: (String, String) -> Unit,
    onShowDialogClick: () -> Unit,
    onDialogDismiss: () -> Unit,
    onHeaderChange: (String) -> Unit,
    onBodyChange: (String) -> Unit
){

    if (showDialog){
        TodoCreateDialog(
            showDialog = showDialog,
            todoDialogHeader = todoDialogHeader,
            todoDialogBody = todoDialogBody,
            onConfirmRequest = addTodo,
            onHeaderChange = onHeaderChange,
            onBodyChange = onBodyChange,
            onDialogDismiss = onDialogDismiss
        )
    }
    TopAppBar(
        title = {
            Text(
                text = "ToDo Tracker"
            )
        },
        actions = {
            IconButton(
                modifier = Modifier,

                onClick = { onShowDialogClick() }) {
                Icon(
                    painter = painterResource(R.drawable.todo_add),
                    contentDescription = "Создать запись"
                )
            }
        }
    )
}
