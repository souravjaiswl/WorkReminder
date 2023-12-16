package com.example.workreminder.usecase

import com.example.workreminder.DataStorage.repositoryImplement
import javax.inject.Inject

class GetNotes @Inject constructor(private val getreposity: repositoryImplement) {
    operator fun invoke()=getreposity.getNotes()
}