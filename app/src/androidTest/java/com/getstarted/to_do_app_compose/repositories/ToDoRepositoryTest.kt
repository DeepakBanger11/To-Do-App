package com.getstarted.to_do_app_compose.repositories

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.getstarted.to_do_app_compose.dataClasses.Priority
import com.getstarted.to_do_app_compose.dataClasses.ToDoTask
import com.getstarted.to_do_app_compose.database.ToDoDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ToDoRepositoryTest(private val toDoDao: ToDoDao) {
    val getAllTask: Flow<List<ToDoTask>> = toDoDao.getAllTasks()


    fun getSortByHighPriority() {
    }

    fun getSelectedTask() {
    }


    suspend fun addTask(toDoTask: ToDoTask) {
        toDoDao.addTask(toDoTask = toDoTask)
    }


    fun updateTask() {
    }

    fun deleteTask() {
    }


    fun deleteAllTasks() {
    }

    fun searchDatabase() {
    }

}