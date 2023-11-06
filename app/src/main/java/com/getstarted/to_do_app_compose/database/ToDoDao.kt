package com.getstarted.to_do_app_compose.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.getstarted.to_do_app_compose.dataClasses.ToDoTask
import kotlinx.coroutines.flow.Flow


@Dao
// data access object
interface ToDoDao {
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    // write your custom queries from Query, insert, update , delete
    // this will select all data in ascending order
    fun getAllTasks(): Flow<List<ToDoTask>>

    //Flow:-An asynchronous data stream that sequentially emits values and completes normally or with an exception
    @Query("SELECT * FROM todo_table WHERE id =:taskId")
    // selecting data with that specific id
    fun getSelectedTask(taskId: Int): Flow<ToDoTask>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    OnConflict strategy constant to ignore the conflict.
//    An Insert DAO method that returns the inserted rows ids will return -1
//    for rows that are not inserted since this strategy will ignore the row if there is a conflict.
    suspend fun addTask(toDoTask: ToDoTask)

    @Update
    suspend fun updateTask(toDoTask: ToDoTask)

    @Delete
    suspend fun deleteTask(toDoTask: ToDoTask)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>>

    @Query(
        """
        SELECT * FROM todo_table ORDER BY 
        CASE 
        WHEN priority LIKE 'L%' THEN 1 
        WHEN priority LIKE 'M%' THEN 2 
        WHEN priority LIKE 'H%' THEN 3 
        END
         """
    )
    fun sortByLowPriorty(): Flow<List<ToDoTask>>

    @Query(
        """
            SELECT * FROM todo_table ORDER BY 
            CASE 
            WHEN priority LIKE 'H%' THEN 1 
            WHEN priority LIKE 'M%' THEN 2 
            WHEN priority LIKE 'L%' THEN 3 END
            """
    )
    fun sortByHighPriorty(): Flow<List<ToDoTask>>
}