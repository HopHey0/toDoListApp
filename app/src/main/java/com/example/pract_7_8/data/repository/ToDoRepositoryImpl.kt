package com.example.pract_7_8.data.repository

import com.example.pract_7_8.data.local.db.Tododao
import com.example.pract_7_8.data.local.db.UserRoomDatabase
import com.example.pract_7_8.data.local.entity.TodoItemEntity
import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.domain.repository.TodoRepository

class ToDoRepositoryImpl(
    private val todoDao: Tododao
) : TodoRepository {

    fun getTodos(): Flow<TodoItemEntity> {
        cachedTodos = todoDao.getTodos()
            .map { TodoItem(it.id, it.title, it.description, it.isCompleted) }
            .toMutableList()
    }

    override suspend fun getTodos(): List<TodoItem> {
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
