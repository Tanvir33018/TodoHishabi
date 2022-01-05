package com.example.hishabikotlin.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataRepository(private val todoDao:TodoDao) {
    val realAll: LiveData<List<TodoModel>> = todoDao.getTaskList()

    suspend fun addTask(todoModel:TodoModel){
        todoDao.addTask(todoModel)
    }

    suspend fun deleteTask(todoModel: TodoModel){
        todoDao.deleteTask(todoModel)
    }

    suspend fun updateTask(todoModel: TodoModel){
        todoDao.updateTask(todoModel)
    }
}