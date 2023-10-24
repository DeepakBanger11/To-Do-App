package com.getstarted.to_do_app_compose.ui.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.getstarted.to_do_app_compose.dataClasses.ToDoTask
import com.getstarted.to_do_app_compose.repositories.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
//The ViewModel annotated with HiltViewModel will be available for creation by the dagger.hilt.android.lifecycle.HiltViewModelFactory
// and can be retrieved by default in an Activity or Fragment annotated with AndroidEntryPoint.
// The HiltViewModel containing a constructor annotated with Inject will have its dependencies defined in the constructor parameters injected by Dagger's Hilt.
class SharedViewModal @Inject constructor(
    private val respository:ToDoRepository
):ViewModel(){
    private val _allTasks =
        MutableStateFlow<List<ToDoTask>>(emptyList())
    val allTasks: StateFlow<List<ToDoTask>> = _allTasks
    fun getAllTasks()
    {
        viewModelScope.launch {
            respository.getAllTask.collect{
                _allTasks.value = it
            }
        }
    }
}