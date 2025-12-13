package com.example.pract_7_8

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.pract_7_8.data.local.TodoJsonDataSource
import com.example.pract_7_8.data.repository.ToDoRepositoryImpl
import com.example.pract_7_8.domain.usecase.GetTodosUseCase
import com.example.pract_7_8.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CheckIfStatusChanged {
    @Test
    fun checkNumOfItem() = runBlocking{
        val dataSource = TodoJsonDataSource(
            context =  ApplicationProvider.getApplicationContext<Context>()
        )
        val repository = ToDoRepositoryImpl(dataSource)
        val getTodosUseCase = GetTodosUseCase(repository)
        val toggleTodoUseCase = ToggleTodoUseCase(repository)

        val sample1Before = getTodosUseCase.invoke().find { it.id == 3 }?.isCompleted
        toggleTodoUseCase.invoke(3)
        val sample1After = getTodosUseCase.invoke().find { it.id == 3 }?.isCompleted

        val sample2Before = getTodosUseCase.invoke().find { it.id == 10 }?.isCompleted
        toggleTodoUseCase.invoke(10)
        val sample2After = getTodosUseCase.invoke().find { it.id == 10 }?.isCompleted

        assert(sample1Before != sample1After)
        assert(sample2Before != sample2After)
    }
}