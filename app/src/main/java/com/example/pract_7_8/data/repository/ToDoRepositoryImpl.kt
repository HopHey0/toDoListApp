package com.example.pract_7_8.data.repository

import com.example.pract_7_8.data.local.db.Tododao
import com.example.pract_7_8.data.local.entity.TodoItemEntity
import com.example.pract_7_8.domain.model.TodoItem
import com.example.pract_7_8.domain.repository.TodoRepository
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

    override suspend fun addTodo(title: String, description: String) {
        todoDao.addTodo(TodoItemEntity(title = title, description = description, isCompleted = false))
    }

    override suspend fun deleteTodo(id: Int) {
        todoDao.deleteTodo(id)
    }

    override fun getTodo(id: Int): Flow<TodoItem>{
        return todoDao.getTodo(id).map { entity ->
            TodoItem(entity.id, entity.title, entity.description, entity.isCompleted)
        }
    }

    override suspend fun updateTodo(id: Int, newTitle: String, newDescription: String){
        todoDao.updateTodo(id, newTitle, newDescription)
    }
}
