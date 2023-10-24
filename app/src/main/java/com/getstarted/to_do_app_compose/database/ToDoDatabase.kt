package com.getstarted.to_do_app_compose.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.getstarted.to_do_app_compose.dataClasses.ToDoTask

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class ToDoDatabase:RoomDatabase() {
    abstract fun toDoDao():ToDoDao
}