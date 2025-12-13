package com.example.pract_7_8.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.presentation.ui.component.ToDoRow
import com.example.pract_7_8.presentation.viewmodel.TodolistViewModel

@Composable
fun ToDoList(
    todolistViewModel: TodolistViewModel,
    onItemClick: (Int) -> Unit
){
    val todos = todolistViewModel.todos
    Column {
        TopBarToDOList()
        LazyColumn(
            modifier = Modifier.padding(horizontal = 15.dp),
        ) {
            items(todos) { item ->
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
fun TopBarToDOList(){
    TopAppBar(
        title = {
            Text(
                text = "ToDo Tracker"
            )
        }
    )
}