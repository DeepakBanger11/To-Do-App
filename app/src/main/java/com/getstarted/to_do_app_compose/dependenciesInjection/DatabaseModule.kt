package com.getstarted.to_do_app_compose.dependenciesInjection

import android.content.Context
import androidx.room.Room
import com.getstarted.to_do_app_compose.database.ToDoDatabase
import com.getstarted.to_do_app_compose.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module //Annotates a class that contributes to the object graph.
@InstallIn(SingletonComponent::class)

//An annotation that declares which component(s) the annotated class should be
//included in when Hilt generates the components. This may only be used with
//classes annotated with @Module or @EntryPoint

object DatabaseModule {
    @Singleton
    //Identifies a type that the injector only instantiates once. Not inherited

    @Provides
//    Annotates methods of a module to create a provider method binding.
//    The method's return type is bound to its returned value.
//    The component implementation will pass dependencies to the method as parameters

    fun provideDatabase(
        @ApplicationContext context:Context
    ) = Room.databaseBuilder(
        context,
        ToDoDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: ToDoDatabase) = database.toDoDao()
}