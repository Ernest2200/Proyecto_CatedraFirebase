package com.example.firebasekotlincrud.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "notes_table")
data class Notes(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "Fecha")
        val date: String,
        @ColumnInfo(name = "Descripción")
        val description: String,
)
