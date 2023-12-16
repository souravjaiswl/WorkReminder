package com.example.workreminder.usecase

import com.example.workreminder.DataStorage.repositoryImplement
import com.example.workreminder.repository.Note
import com.example.workreminder.repository.Repository

class InsertNote constructor(private val insertreposity: repositoryImplement) {
    suspend operator fun invoke(note: Note)=insertreposity.insertNote(note)
}