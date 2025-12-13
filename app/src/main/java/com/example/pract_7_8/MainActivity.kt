package com.example.pract_7_8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pract_7_8.data.local.TodoJsonDataSource
import com.example.pract_7_8.data.repository.ToDoRepositoryImpl
import com.example.pract_7_8.domain.repository.TodoRepository
import com.example.pract_7_8.domain.usecase.GetTodosUseCase
import com.example.pract_7_8.domain.usecase.ToggleTodoUseCase
import com.example.pract_7_8.presentation.ui.screen.ToDoList
import com.example.pract_7_8.presentation.viewmodel.TodolistViewModel
import com.example.pract_7_8.ui.theme.Pract_7_8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val dataSource = TodoJsonDataSource(this)
        val repository = ToDoRepositoryImpl(dataSource)
        val getTodosUseCase = GetTodosUseCase(repository)
        val toggleTodoUseCase = ToggleTodoUseCase(repository)
        val viewModel = TodolistViewModel(
            getTodosUseCase = getTodosUseCase,
            toggleTodoUseCase = toggleTodoUseCase
        )
        setContent {
            Pract_7_8Theme {
                Surface {
                    ToDoList(
                        viewModel,
                        onItemClick = {   }
                    )
                }
            }
        }
    }
}