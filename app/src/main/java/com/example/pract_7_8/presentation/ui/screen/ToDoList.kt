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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pract_7_8.R
import com.example.pract_7_8.presentation.ui.component.ToDoRow
import com.example.pract_7_8.presentation.ui.component.TodoCreateDialog
import com.example.pract_7_8.presentation.viewmodel.TodolistViewModel

@Composable
fun ToDoList(
    todolistViewModel: TodolistViewModel,
    onItemClick: (Int) -> Unit,
    modifier: Modifier
){
    val todos = todolistViewModel.todos.observeAsState(emptyList())
    Column() {
        TopBarToDoList(todolistViewModel)
        LazyColumn(
            modifier = modifier
                .testTag("homeScreen")
                .padding(horizontal = 15.dp),
        ) {
            items(todos.value) { item ->
                ToDoRow(
                    item,
                    { onItemClick(item.id) },
                    { todolistViewModel.onToggleTodo(item.id) }

                )
                Spacer(modifier = Modifier.padding(vertical = 5.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarToDoList(
    todolistViewModel: TodolistViewModel
){
    val openDialog = rememberSaveable { mutableStateOf(false) }
    if (openDialog.value){
        TodoCreateDialog(openDialog, { }, { todolistViewModel.addTodo(it)})
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

                onClick = { openDialog.value = true }) {
                Icon(
                    painter = painterResource(R.drawable.todo_add),
                    contentDescription = "Создать запись"
                )
            }
        }
    )
}
