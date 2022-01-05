package com.example.hishabikotlin.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    val mId:Int,
    val mTitle:String,
    val mDescription:String,
    var mActive:Int
    )