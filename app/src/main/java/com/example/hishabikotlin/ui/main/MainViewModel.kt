package com.example.hishabikotlin.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.example.hishabikotlin.database.DataRepository
import com.example.hishabikotlin.database.TodoDatabase
import com.example.hishabikotlin.database.TodoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val getTask: LiveData<List<TodoModel>>
    private val mRepository:DataRepository

    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        getTask = todoDao.getTaskList()
        mRepository = DataRepository(todoDao)
    }

    fun addTask(todoModel:TodoModel){
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.addTask(todoModel)
        }
    }

    fun deleteTask(todoModel: TodoModel){
        viewModelScope.launch(Dispatchers.IO){
            mRepository.deleteTask(todoModel)
        }
    }

    fun updateTask(todoModel: TodoModel){
        viewModelScope.launch(Dispatchers.IO){
            mRepository.updateTask(todoModel)
        }
    }
}