package com.getstarted.to_do_app_compose.ui.viewmodal

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.getstarted.to_do_app_compose.dataClasses.ToDoTask
import com.getstarted.to_do_app_compose.repositories.ToDoRepository
import com.getstarted.to_do_app_compose.util.RequestState
import com.getstarted.to_do_app_compose.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
//The ViewModel annotated with HiltViewModel will be available for creation by the dagger.hilt.android.lifecycle.HiltViewModelFactory
// and can be retrieved by default in an Activity or Fragment annotated with AndroidEntryPoint.
// The HiltViewModel containing a constructor annotated with Inject will have its dependencies defined in the constructor parameters injected by Dagger's Hilt.
class SharedViewModal @Inject constructor(
    private val respository: ToDoRepository
) : ViewModel() {

    // this is to keep searchAppBarSate Closed when we open up
     val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)

    //this is set search text
     val searchTextState: MutableState<String> = mutableStateOf("")

    private val _allTasks =
        MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<ToDoTask>>> = _allTasks
    fun getAllTasks() {
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                respository.getAllTask.collect {
                    _allTasks.value = RequestState.Success(it)
                }
            }
        }
        catch (e:Exception){
            _allTasks.value = RequestState.Error(e)
        }
    }
    private  val _selectedTask: MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask:StateFlow<ToDoTask?> = _selectedTask
    fun getSelectedTask(taskId:Int){
        viewModelScope.launch {
            respository.getSelectedTask(taskId = taskId).collect{ task->
                _selectedTask.value = task
            }
        }
    }
}