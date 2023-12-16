package com.example.workreminder.toodo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workreminder.repository.Note
import com.example.workreminder.usecase.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val useCase: NoteUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var title by mutableStateOf("")
    var discription by mutableStateOf("")
    var modeColor by mutableStateOf(0)

    val id = savedStateHandle.get<Int>(key = "id")

    init {
        viewModelScope.launch {
            val note = id?.let { useCase.getByIdNote(it) }
            if (note != null) {
                title = note.DataTitle
                discription = note.DataDescription
                modeColor = note.DataColor
            }
        }
    }

    fun updateNote() = viewModelScope.launch {
        useCase.updateNote(Note(id!!, title, DataDescription = discription, DataColor = modeColor))
    }

    fun deleteNote(note: Note)=viewModelScope.launch {
        useCase.deleteNote(note)
    }

}