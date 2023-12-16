package com.example.workreminder.usecase

import com.example.workreminder.DataStorage.repositoryImplement
import javax.inject.Inject

class GetByIdNote @Inject constructor(private val getIdreposity: repositoryImplement) {
    suspend operator fun invoke(id:Int)=getIdreposity.getByIdNote(id)
}