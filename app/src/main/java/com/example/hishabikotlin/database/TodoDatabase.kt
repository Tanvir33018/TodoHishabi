package com.example.hishabikotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [TodoModel::class],version = 2,exportSchema = false)
abstract class TodoDatabase:RoomDatabase() {

    abstract fun todoDao():TodoDao
    companion object{
        val mMigration_1_2= object:Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE todo_table ADD COLUMN mActive INTEGER NOT NULL DEFAULT(0)")
            }

        }
        @Volatile
        var INSTACE:TodoDatabase?=null
        fun getDatabase(mContext:Context):TodoDatabase{
            val mInstance = INSTACE
            if (mInstance!=null)
                return mInstance
            synchronized(this){
                val mDatabaseInstance = Room.databaseBuilder(mContext.applicationContext,
                TodoDatabase::class.java,
                "todo_database").addMigrations(mMigration_1_2).build()
                INSTACE = mDatabaseInstance
                return mDatabaseInstance
            }
          }
    }
}