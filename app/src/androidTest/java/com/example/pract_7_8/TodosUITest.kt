package com.example.pract_7_8

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.example.pract_7_8.data.local.TodoJsonDataSource
import com.example.pract_7_8.data.repository.ToDoRepositoryImpl
import com.example.pract_7_8.domain.usecase.GetTodosUseCase
import com.example.pract_7_8.domain.usecase.ToggleTodoUseCase
import com.example.pract_7_8.navigation.AppNavHost
import com.example.pract_7_8.presentation.viewmodel.TodolistViewModel
import com.example.pract_7_8.ui.theme.Pract_7_8Theme
import org.junit.Rule
import org.junit.Test


class TodosUITest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun checkIfTodosOnScreen(){
        val dataSource = TodoJsonDataSource(context)
        val repository = ToDoRepositoryImpl(dataSource)
        val getTodosUseCase = GetTodosUseCase(repository)
        val toggleTodoUseCase = ToggleTodoUseCase(repository)
        val viewModel = TodolistViewModel(
            getTodosUseCase = getTodosUseCase,
            toggleTodoUseCase = toggleTodoUseCase
        )
        composeTestRule.setContent {
            Pract_7_8Theme {
                val navController: NavHostController = rememberNavController()
                AppNavHost(viewModel, navController, Modifier)
            }
        }

        /*
        Содержимое первых трех задач из JSON-файла
        [
          {"id": 1,"title": "Купить молоко","description": "2 литра, обезжиренное","isCompleted": false},
          {"id": 2,"title": "Позвонить маме","description": "Спросить про выходные","isCompleted": true},
          {"id": 3,"title": "Сделать ДЗ по Android","description": "Clean Architecture + Compose","isCompleted": false}
        ]
         */

        composeTestRule.onNodeWithText("Купить молоко").assertIsDisplayed()
        composeTestRule.onNodeWithText("Спросить про выходные").assertIsDisplayed()
        composeTestRule.onNodeWithText("Сделать ДЗ по Android").assertIsDisplayed()
    }

}
