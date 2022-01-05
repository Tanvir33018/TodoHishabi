package com.example.hishabikotlin.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Insert
    fun addTask(data:TodoModel)

    @Delete
    fun deleteTask(todoModel: TodoModel)

    @Update
    fun updateTask(todoModel: TodoModel)

    @Query("SELECT * FROM todo_table ORDER BY mId ASC")
    fun getTaskList(): LiveData<List<TodoModel>>
}