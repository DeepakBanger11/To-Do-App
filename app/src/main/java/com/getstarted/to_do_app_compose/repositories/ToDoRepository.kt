package com.getstarted.to_do_app_compose.repositories

import com.getstarted.to_do_app_compose.dataClasses.ToDoTask
import com.getstarted.to_do_app_compose.database.ToDoDao
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped //Scope annotation for bindings that should exist for the life of a a single ViewModel.
class ToDoRepository @Inject constructor(private val toDoDao:ToDoDao) {
    val getAllTask: Flow<List<ToDoTask>> = toDoDao.getAllTasks()
    val sortByLowPriority: Flow<List<ToDoTask>> = toDoDao.sortByLowPriorty()
    val sortByHighPriority: Flow<List<ToDoTask>> = toDoDao.sortByHighPriorty()

    fun getSelectedTask(taskId:Int): Flow<ToDoTask>{
        return toDoDao.getSelectedTask(taskId = taskId)
    }
    suspend fun addTask(toDoTask: ToDoTask) {
        toDoDao.addTask(toDoTask = toDoTask)
    }

    suspend fun updateTask(toDoTask: ToDoTask) {
        toDoDao.updateTask(toDoTask = toDoTask)
    }

    suspend fun deleteTask(toDoTask: ToDoTask) {
        toDoDao.deleteTask(toDoTask = toDoTask)
    }
    suspend fun deleteAllTasks() {
        toDoDao.deleteAllTasks()
    }
    fun searchDatabase(searchQuery:String): Flow<List<ToDoTask>>{
        return toDoDao.searchDatabase(searchQuery =searchQuery)
    }

}