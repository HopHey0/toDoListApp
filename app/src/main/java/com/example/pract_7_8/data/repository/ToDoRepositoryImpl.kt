package com.example.pract_7_8.data.repository

import com.example.pract_7_8.data.local.db.Tododao
import com.example.pract_7_8.data.local.entity.TodoItemEntity
import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.domain.repository.TodoRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ToDoRepositoryImpl(
    private val todoDao: Tododao
) : TodoRepository {


    override fun getTodos(): Flow<List<TodoItem>> {
        return todoDao.getTodos().map {list ->
            list.map { TodoItem(it.id, it.title, it.description, it.isCompleted) }
        }
    }


    override suspend fun toggleTodo(id: Int) {
        todoDao.toggleTodo(id)
    }

    override suspend fun addTodo(item: TodoItem) {
        todoDao.addTodo(TodoItemEntity(title = item.title, description = item.description, isCompleted = item.isCompleted))
    }

    override suspend fun deleteTodo(id: Int) {
        todoDao.deleteTodo(id)
    }
}
