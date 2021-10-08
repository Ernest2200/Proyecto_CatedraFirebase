package com.example.firebasekotlincrud.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.firebasekotlincrud.entities.Notes
import kotlinx.coroutines.flow.Flow
@Dao
interface NotesDao {
    @Query("SELECT * FROM notes_table ORDER BY name ASC")
    fun getAlphabetizedNotes(): Flow<List<Notes>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: Notes)
    @Query("DELETE FROM notes_table")
    suspend fun deleteAll()
    @Query("DELETE FROM notes_table WHERE id=:id")
    suspend fun deleteOne(id:Int)
}