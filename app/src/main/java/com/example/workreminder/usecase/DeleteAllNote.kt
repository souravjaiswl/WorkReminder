package com.example.workreminder.usecase

import com.example.workreminder.DataStorage.repositoryImplement
import javax.inject.Inject


class DeleteAllNotes @Inject constructor(private val repository: repositoryImplement) {
    suspend operator fun invoke() {
        repository.deleteAllNotes()
    }
}