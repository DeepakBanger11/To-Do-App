package com.getstarted.to_do_app_compose.dataClasses

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.getstarted.to_do_app_compose.util.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class ToDoTask(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val title:String,
    val description:String,
    val priority: Priority


)
{

}
