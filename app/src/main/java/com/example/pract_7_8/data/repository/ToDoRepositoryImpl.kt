package com.example.pract_7_8.data.repository

import com.example.pract_7_8.data.local.TodoJsonDataSource
import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.domain.repository.TodoRepository

class ToDoRepositoryImpl(
    private val dataSource: TodoJsonDataSource
) : TodoRepository {

    private var cachedTodos: MutableList<TodoItem> = mutableListOf()

    override suspend fun getTodos(): List<TodoItem> {
        cachedTodos = dataSource.getTodos()
            .map { TodoItem(it.id, it.title, it.description, it.isCompleted) }
            .toMutableList()
        return cachedTodos
    }

    override suspend fun toggleTodo(id: Int) {
        cachedTodos = cachedTodos.map {
            if (it.id == id) {
                it.copy(isCompleted = !it.isCompleted)
            } else {
                it
            }
        }.toMutableList()
    }
}
