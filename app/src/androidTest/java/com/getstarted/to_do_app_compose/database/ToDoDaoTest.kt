package com.getstarted.to_do_app_compose.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.getstarted.to_do_app_compose.dataClasses.Priority
import com.getstarted.to_do_app_compose.dataClasses.ToDoTask
import com.getstarted.to_do_app_compose.repositories.ToDoRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ToDoDaoTest {
    private lateinit var database: ToDoDatabase
    private lateinit var dao: ToDoDao
    private lateinit var toDoRepository: ToDoRepository

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ToDoDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.toDoDao()
        toDoRepository = ToDoRepository(toDoDao = dao)
        val task1 =
            ToDoTask(
                id = 1,
                title = "Spiderman",
                description = "web",
                priority = Priority.LOW
            )
        val task2 = ToDoTask(
            id = 3,
            title = "Superman",
            description = "superpower",
            priority = Priority.HIGH
        )
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun check_No_Of_Entries_Into_Database() = runTest {
        var entries = 0
        toDoRepository.getAllTask.test {
            entries = awaitItem().size
        }
        assertThat(entries).isEqualTo(0)
    }

    @Test
    fun check_Entry_Into_Database() = runTest {
        val allTask = ToDoTask(
            id = 1,
            title = "Spiderman",
            description = "web",
            priority = Priority.NONE
        )
        toDoRepository.addTask(toDoTask = allTask)

        val newtask = mutableListOf<ToDoTask>()

        toDoRepository.getAllTask.test { newtask.addAll(awaitItem()) }
        val expectedTaskList = listOf(allTask)

        assertThat(newtask).isEqualTo(expectedTaskList)

    }

    @Test
    fun get_Selected_Task_From_Database() = runTest {
        val allTask = ToDoTask(
            id = 1,
            title = "Spiderman",
            description = "web",
            priority = Priority.NONE
        )
        toDoRepository.addTask(toDoTask = allTask)
        val newtask = mutableListOf<ToDoTask>()
        toDoRepository.getSelectedTask(1).test { newtask.addAll(listOf(awaitItem())) }

        toDoRepository.getAllTask.test { newtask.addAll(awaitItem()) }

        assertThat(allTask).isIn(newtask)
    }

    @Test
    fun update_Task_From_Database() = runTest {
        val allTask =
            ToDoTask(
                id = 1,
                title = "Spiderman",
                description = "web",
                priority = Priority.NONE
            )
        val updatedTask = ToDoTask(
            id = 1,
            title = "Superman",
            description = "superpower",
            priority = Priority.HIGH
        )
        toDoRepository.addTask(toDoTask = allTask)
        toDoRepository.updateTask(updatedTask)

        val newtask = mutableListOf<ToDoTask>()

        val expectedTaskList = listOf(updatedTask)
        toDoRepository.getAllTask.test { newtask.addAll(awaitItem()) }

        assertThat(expectedTaskList).isEqualTo(newtask)
    }

    @Test
    fun delete_All_Task_From_Database() = runTest {
        val task1 =
            ToDoTask(
                id = 1,
                title = "Spiderman",
                description = "web",
                priority = Priority.NONE
            )
        val task2 = ToDoTask(
            id = 2,
            title = "Superman",
            description = "superpower",
            priority = Priority.HIGH
        )

        toDoRepository.addTask(toDoTask = task1)
        toDoRepository.addTask(toDoTask = task2)
        toDoRepository.deleteAllTasks()

        var entries = 0
        toDoRepository.getAllTask.test {
            entries = awaitItem().size
        }
        assertThat(entries).isEqualTo(0)
    }

    fun delete_Task_From_Database() = runTest {
        val allTask =
            ToDoTask(
                id = 1,
                title = "Spiderman",
                description = "web",
                priority = Priority.NONE
            )

        toDoRepository.addTask(toDoTask = allTask)
        toDoRepository.deleteTask(allTask)

        var entries = 0
        toDoRepository.getAllTask.test {
            entries = awaitItem().size
        }
        assertThat(entries).isEqualTo(0)
    }

    @Test
    fun search_From_Database() = runTest {
        val task1 =
            ToDoTask(
                id = 1,
                title = "Spiderman",
                description = "web",
                priority = Priority.NONE
            )
        val task2 = ToDoTask(
            id = 3,
            title = "Superman",
            description = "superpower",
            priority = Priority.HIGH
        )

        toDoRepository.addTask(toDoTask = task1)
        toDoRepository.addTask(toDoTask = task2)
        val searchedTask = mutableListOf<ToDoTask>()

        val expectedTaskList = listOf(task1)
        toDoRepository.searchDatabase("web").test {
            searchedTask.addAll(awaitItem())
        }
        assertThat(expectedTaskList).isEqualTo(searchedTask)
    }

    @Test
    fun sort_By_Low_Priorty_Database() = runTest {
        val task1 =
            ToDoTask(
                id = 1,
                title = "Spiderman",
                description = "web",
                priority = Priority.LOW
            )
        val task2 = ToDoTask(
            id = 3,
            title = "Superman",
            description = "superpower",
            priority = Priority.HIGH
        )

        toDoRepository.addTask(toDoTask = task1)
        toDoRepository.addTask(toDoTask = task2)

        val sortedTask = mutableListOf<ToDoTask>()
        val expectedTaskList = listOf(task1,task2)
        toDoRepository.sortByLowPriority.test {
             sortedTask.addAll(awaitItem())
        }
        assertThat(sortedTask).isEqualTo(expectedTaskList)
    }
    @Test
    fun sort_By_High_Priorty_Database() = runTest {
        val task1 =
            ToDoTask(
                id = 1,
                title = "Spiderman",
                description = "web",
                priority = Priority.LOW
            )
        val task2 = ToDoTask(
            id = 3,
            title = "Superman",
            description = "superpower",
            priority = Priority.HIGH
        )
        toDoRepository.addTask(toDoTask = task1)
        toDoRepository.addTask(toDoTask = task2)

        val sortedTask = mutableListOf<ToDoTask>()
        val expectedTaskList = listOf(task2,task1)
        toDoRepository.sortByHighPriority.test {
            sortedTask.addAll(awaitItem())
        }
        assertThat(sortedTask).isEqualTo(expectedTaskList)
    }


}