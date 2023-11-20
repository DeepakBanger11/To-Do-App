package com.getstarted.to_do_app_compose.ui.viewmodal


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.getstarted.to_do_app_compose.dataClasses.Priority
import com.getstarted.to_do_app_compose.dataClasses.ToDoTask
import com.getstarted.to_do_app_compose.repositories.DataStoreRepository
import com.getstarted.to_do_app_compose.repositories.ToDoRepository
import com.getstarted.to_do_app_compose.util.RequestState
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
@OptIn(ExperimentalCoroutinesApi::class)
class SharedViewModalTest{
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val mockRepository: ToDoRepository = mockk(relaxed = true)
    private  var dataStoreRepository: DataStoreRepository = mockk()
    private  var viewModel: SharedViewModal = SharedViewModal(
        mockRepository,
        dataStoreRepository
    )

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
    }
    @AfterEach
    fun close() {
        Dispatchers.resetMain()
        Dispatchers.shutdown()
        clearAllMocks()
    }
    @Test
    fun `get all tasks from database`() = runTest{
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
        var sizeOfTask = listOf<ToDoTask>()
        coEvery {
            mockRepository.addTask(task1)
        }returns Unit


        viewModel.allTasks.test {
           assertThat(awaitItem()) is RequestState.Success<*>
        }



    // here you are mocking the response of getAllTask() from repo

        assertThat(viewModel.getSelectedTask(1)).isEqualTo(task1)



    }

}

