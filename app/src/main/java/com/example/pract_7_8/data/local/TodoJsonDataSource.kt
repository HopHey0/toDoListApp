package com.example.pract_7_8.data.local

import android.content.Context
import com.example.pract_7_8.R
import com.example.pract_7_8.data.model.TodoItemDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TodoJsonDataSource(private val context: Context) {
    private val gson = Gson()

    fun getTodos(): List<TodoItemDto> {
        val inputStream = context.resources.openRawResource(R.raw.todos)
        val json = inputStream.bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<TodoItemDto>>() {}.type
        return gson.fromJson(json, type)
    }
}
