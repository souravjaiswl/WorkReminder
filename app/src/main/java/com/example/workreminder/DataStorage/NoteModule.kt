package com.example.workreminder.DataStorage

import android.content.Context
import androidx.room.Room
import com.example.workreminder.repository.Repository
import com.example.workreminder.usecase.DeleteAllNotes
import com.example.workreminder.usecase.DeleteNote
import com.example.workreminder.usecase.GetByIdNote
import com.example.workreminder.usecase.GetNotes
import com.example.workreminder.usecase.InsertNote
import com.example.workreminder.usecase.NoteUseCase
import com.example.workreminder.usecase.UpdateNote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {
    @Provides
    fun provideDataBase(@ApplicationContext context: Context):TaskDetails= Room.databaseBuilder(
        context=context,
        klass = TaskDetails::class.java,
        name = "NoteDB"
    ).build()

    @Provides
    fun provideDao(dao: TaskDetails)=dao.note()

    @Provides
    fun provideNoteUseCase(repository: repositoryImplement)=NoteUseCase(
        getNotes = GetNotes(repository),
        getByIdNote = GetByIdNote(repository),
        deleteNote = DeleteNote(repository),
        updateNote = UpdateNote(repository),
        insertNote = InsertNote(repository),
        deleteAllNotes = DeleteAllNotes(repository)
    )
}