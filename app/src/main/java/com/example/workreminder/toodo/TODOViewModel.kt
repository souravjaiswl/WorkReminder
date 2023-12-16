package com.example.workreminder.toodo

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workreminder.repository.Note
import com.example.workreminder.usecase.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TODOViewModel @Inject constructor(private val useCase: NoteUseCase) : ViewModel() {
    val notes = useCase.getNotes()

    fun deleteNote(note: Note) = viewModelScope.launch {
        useCase.deleteNote(note)
    }

    // Function to delete all notes
    fun deleteAllNotes() = viewModelScope.launch {
        useCase.deleteAllNotes()
    }
}