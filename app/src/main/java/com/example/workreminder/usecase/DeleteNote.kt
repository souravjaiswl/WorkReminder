package com.example.workreminder.usecase


import com.example.workreminder.DataStorage.repositoryImplement
import com.example.workreminder.repository.Note
import javax.inject.Inject

class DeleteNote @Inject constructor(private val deletreposity: repositoryImplement) {
    suspend operator fun invoke(note: Note)=deletreposity.deleteNote(note)
}