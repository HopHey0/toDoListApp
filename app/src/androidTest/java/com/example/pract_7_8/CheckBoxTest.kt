package com.example.pract_7_8

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
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
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CheckBoxTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    private lateinit var viewModel: TodolistViewModel

    @Before
    fun initActivity(){
        val dataSource = TodoJsonDataSource(context)
        val repository = ToDoRepositoryImpl(dataSource)
        val getTodosUseCase = GetTodosUseCase(repository)
        val toggleTodoUseCase = ToggleTodoUseCase(repository)
        viewModel = TodolistViewModel(
            getTodosUseCase = getTodosUseCase,
            toggleTodoUseCase = toggleTodoUseCase
        )
        composeTestRule.setContent {
            Pract_7_8Theme {
                val navController: NavHostController = rememberNavController()
                AppNavHost(viewModel, navController, Modifier)
            }
        }
    }

    @Test
    fun checkIfBoxIsSelectedAfterClick(){
        composeTestRule.onNodeWithTag("RowCheckBox3")
            .assertIsOff()
            .performClick()
            .assertIsOn()

        composeTestRule.onNodeWithTag("RowCheckBox4")
            .assertIsOn()
            .performClick()
            .performClick()
            .assertIsOn()

        composeTestRule.onNodeWithTag("RowCheckBox10")
            .performClick()
            .performClick()
            .performClick()
            .assertIsOff()

    }

    @Test
    fun checkIfDataChangedAfterClick() {
        val sample1Before = viewModel.todos.find { it.id == 3}?.isCompleted
        composeTestRule.onNodeWithTag("RowCheckBox3").performClick()
        val sample1After = viewModel.todos.find { it.id == 3}?.isCompleted

        val sample2Before = viewModel.todos.find { it.id == 7}?.isCompleted
        composeTestRule.onNodeWithTag("RowCheckBox7").performClick()
        composeTestRule.onNodeWithTag("RowCheckBox7").performClick()
        val sample2After = viewModel.todos.find { it.id == 7}?.isCompleted

        assert(sample1Before != sample1After)
        assert(sample2Before == sample2After)
    }
}