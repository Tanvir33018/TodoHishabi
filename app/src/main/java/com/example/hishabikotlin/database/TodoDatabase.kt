package com.example.hishabikotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [TodoModel::class],version = 1,exportSchema = false)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun todoDao():TodoDao
    companion object{
        @Volatile
        var INSTACE:TodoDatabase?=null
        fun getDatabase(mContext:Context):TodoDatabase{
            val mInstance = INSTACE
            if (mInstance!=null)
                return mInstance
            synchronized(this){
                val mDatabaseInstance = Room.databaseBuilder(mContext.applicationContext,
                TodoDatabase::class.java,
                "todo_database").build()
                INSTACE = mDatabaseInstance
                return mDatabaseInstance
            }
          }
    }
}