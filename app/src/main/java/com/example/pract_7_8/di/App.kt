package com.example.pract_7_8.di

import android.app.Application
import com.example.pract_7_8.data.local.db.AppDataBase
import com.example.pract_7_8.data.repository.ToDoRepositoryImpl
import com.example.pract_7_8.domain.repository.TodoRepository
import com.example.pract_7_8.domain.usecase.AddTodoUseCase
import com.example.pract_7_8.domain.usecase.DeleteTodoUseCase
import com.example.pract_7_8.domain.usecase.GetSingleTodoUseCase
import com.example.pract_7_8.domain.usecase.GetTodosUseCase
import com.example.pract_7_8.domain.usecase.ToggleTodoUseCase
import com.example.pract_7_8.domain.usecase.UpdateTodoUseCase
import com.example.pract_7_8.presentation.viewmodel.TodoDetailsViewModel
import com.example.pract_7_8.presentation.viewmodel.TodoListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class App: Application() {
    val todoModule = module {
        single { AppDataBase.getInstance(androidContext()) }
        single { get<AppDataBase>().todoDao() }
        single<TodoRepository> { ToDoRepositoryImpl(get()) }

        factory { AddTodoUseCase(get()) }
        factory { DeleteTodoUseCase(get()) }
        factory { GetTodosUseCase(get()) }
        factory { ToggleTodoUseCase(get()) }
        factory { GetSingleTodoUseCase(get()) }
        factory { UpdateTodoUseCase(get()) }

        viewModel { TodoListViewModel(get(), get(), get(), get()) }
        viewModel { TodoDetailsViewModel(get(), get()) }

    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(todoModule)
        }
    }
}