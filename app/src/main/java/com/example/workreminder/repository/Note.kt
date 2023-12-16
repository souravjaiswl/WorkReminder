package com.example.workreminder.repository


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val DataTitle: String = "",
    val DataDescription: String = "",
    val DataColor: Int = 0,
)