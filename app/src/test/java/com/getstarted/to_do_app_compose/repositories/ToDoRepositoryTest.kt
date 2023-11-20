package com.getstarted.to_do_app_compose.repositories
//
//import com.getstarted.to_do_app_compose.dataClasses.ToDoTask
//import com.getstarted.to_do_app_compose.database.ToDoDao
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.MutableStateFlow
//
//abstract class FakeToDoRepository(toDoDao: ToDoDao) : ToDoRepository(toDoDao) {
//
//     val tasks = mutableListOf<ToDoTask>()
//     val _allTasksStateFlow = MutableStateFlow<List<ToDoTask>>(emptyList())
//
//    override val getAllTasks: Flow<List<ToDoTask>> = _allTasksStateFlow
//    override val sortByLowPriority: Flow<List<ToDoTask>> = _allTasksStateFlow
//    override val sortByMediumPriority: Flow<List<ToDoTask>> = _allTasksStateFlow
//    override val sortByHighPriority: Flow<List<ToDoTask>> = _allTasksStateFlow
//
//    override fun getSelectedTask(taskId: Int): Flow<ToDoTask> {
//        val selectedTask = tasks.find { it.id == taskId }
//        return MutableStateFlow(selectedTask)
//    }
//
//    override suspend fun addTask(toDoTask: ToDoTask) {
//        tasks.add(toDoTask)
//        updateAllTasksState()
//    }
//
//    override suspend fun updateTask(toDoTask: ToDoTask) {
//        val index = tasks.indexOfFirst { it.id == toDoTask.id }
//        if (index != -1) {
//            tasks[index] = toDoTask
//            updateAllTasksState()
//        }
//    }
//
//    override suspend fun deleteTask(toDoTask: ToDoTask) {
//        tasks.remove(toDoTask)
//        updateAllTasksState()
//    }
//
//    override suspend fun deleteAllTasks() {
//        tasks.clear()
//        updateAllTasksState()
//    }
//
//    override fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>> {
//        val searchedTasks = tasks.filter { it.title.contains(searchQuery, ignoreCase = true) }
//        return MutableStateFlow(searchedTasks)
//    }
//
//    private fun updateAllTasksState() {
//        _allTasksStateFlow.value = tasks
//    }
//}