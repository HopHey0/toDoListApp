package com.example.pract_7_8.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pract_7_8.data.local.entity.TodoItemEntity

@Database(entities = [TodoItemEntity::class], version = 2, exportSchema = true)
abstract class AppDataBase: RoomDatabase() {

    abstract fun todoDao(): Tododao

    companion object {
        private var INSTANCE: AppDataBase? = null
        fun getInstance(context: Context): AppDataBase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                                        context.applicationContext,
                                        AppDataBase::class.java,
                                        "todoDB"
                                    )
                        .fallbackToDestructiveMigration(false)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

//    init {
//        val gson = Gson()
//        val json = INSTANCE.assets.open("todos.json").bufferedReader().use { it.readText() }
//        val type = object : TypeToken<List<TodoItemEntity>>() {}.type
//        val todos = gson.fromJson(json, type)
//        todoDao.insertAll(todos)
//    }
}