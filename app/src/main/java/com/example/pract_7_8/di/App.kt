package com.example.pract_7_8.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.pract_7_8.data.local.db.AppDataBase
import com.example.pract_7_8.data.preferences.UserPreferencesRepositoryImpl
import com.example.pract_7_8.data.repository.ToDoRepositoryImpl
import com.example.pract_7_8.domain.repository.TodoRepository
import com.example.pract_7_8.domain.repository.UserPreferencesRepository
import com.example.pract_7_8.domain.usecase.preferencesUseCases.GetCompletedColorUseCase
import com.example.pract_7_8.domain.usecase.preferencesUseCases.SetCompletedColorUseCase
import com.example.pract_7_8.domain.usecase.todoUseCases.AddTodoUseCase
import com.example.pract_7_8.domain.usecase.todoUseCases.DeleteTodoUseCase
import com.example.pract_7_8.domain.usecase.todoUseCases.GetSingleTodoUseCase
import com.example.pract_7_8.domain.usecase.todoUseCases.GetTodosUseCase
import com.example.pract_7_8.domain.usecase.todoUseCases.ToggleTodoUseCase
import com.example.pract_7_8.domain.usecase.todoUseCases.UpdateTodoUseCase
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
        factory { GetCompletedColorUseCase(get()) }
        factory { SetCompletedColorUseCase(get()) }

        viewModel { TodoListViewModel(get(), get(), get(), get(), get(), get()) }
        viewModel { TodoDetailsViewModel(get(), get()) }

    }

    val dataStoreModule = module {
        single<DataStore<Preferences>> {
            PreferenceDataStoreFactory.create(
                produceFile = { androidContext().preferencesDataStoreFile("app_preferences") }
            )
        }
        single<UserPreferencesRepository> { UserPreferencesRepositoryImpl(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(todoModule, dataStoreModule)
        }
    }
}