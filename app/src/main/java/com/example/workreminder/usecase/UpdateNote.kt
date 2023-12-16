package com.example.workreminder.usecase

import com.example.workreminder.DataStorage.repositoryImplement
import com.example.workreminder.repository.Note
import javax.inject.Inject

class UpdateNote @Inject constructor(private val updatereposity: repositoryImplement) {
    suspend operator fun invoke(note: Note)=updatereposity.updateNote(note)
}