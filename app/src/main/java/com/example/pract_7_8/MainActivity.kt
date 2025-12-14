package com.example.pract_7_8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pract_7_8.data.local.db.AppDataBase
import com.example.pract_7_8.data.repository.ToDoRepositoryImpl
import com.example.pract_7_8.domain.usecase.AddTodoUseCase
import com.example.pract_7_8.domain.usecase.GetTodosUseCase
import com.example.pract_7_8.domain.usecase.ToggleTodoUseCase
import com.example.pract_7_8.navigation.AppNavHost
import com.example.pract_7_8.presentation.viewmodel.TodolistViewModel
import com.example.pract_7_8.ui.theme.Pract_7_8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val dataSource = AppDataBase.getInstance(this)
        val repository = ToDoRepositoryImpl(dataSource.todoDao())
        val getTodosUseCase = GetTodosUseCase(repository)
        val toggleTodoUseCase = ToggleTodoUseCase(repository)
        val addTodoUseCase = AddTodoUseCase(repository)
        val viewModel = TodolistViewModel(
            getTodosUseCase = getTodosUseCase,
            toggleTodoUseCase = toggleTodoUseCase,
            addTodoUseCase = addTodoUseCase
        )
        setContent {
            Pract_7_8Theme {
                Scaffold { innerPadding ->
                    val navController: NavHostController = rememberNavController()
                    AppNavHost(viewModel, navController, Modifier.padding(innerPadding))
                }
            }
        }
    }
}