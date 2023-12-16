package com.example.workreminder.DataStorage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.workreminder.repository.Note
import com.example.workreminder.repository.Repository


@Database(entities = [Note::class], exportSchema = false, version = 1)
abstract class TaskDetails : RoomDatabase() {
    abstract fun note(): Repository
}