package com.example.pract_7_8

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.pract_7_8.data.local.TodoJsonDataSource
import com.example.pract_7_8.data.repository.ToDoRepositoryImpl
import com.example.pract_7_8.domain.usecase.GetTodosUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetTodosUseCaseTest {
    @Test
    fun checkNumOfItem() = runBlocking{
        val dataSource = TodoJsonDataSource(
            context =  ApplicationProvider.getApplicationContext<Context>()
        )
        val repository = ToDoRepositoryImpl(dataSource)
        val getTodosUseCase = GetTodosUseCase(repository)
        assert(!getTodosUseCase.invoke().isEmpty())
        assert(getTodosUseCase.invoke().size == 12)
    }
}