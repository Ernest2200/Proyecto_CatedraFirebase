package com.example.firebasekotlincrud.repository

import android.content.Context
import com.example.firebasekotlincrud.dao.NotesDao
import com.example.firebasekotlincrud.database.NotesRoomDatabase
import com.example.firebasekotlincrud.entities.Notes
import kotlinx.coroutines.flow.Flow
// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class NotesRepository(private val notesDao: NotesDao) {
    companion object {
        private var INSTANCE : NotesRepository? = null
        fun getRepository(context: Context) : NotesRepository {
            return INSTANCE ?: synchronized(this) {
                val database = NotesRoomDatabase.getDatabase(context)
                val instance = NotesRepository(database.notesDao())
                INSTANCE = instance
                instance
            }
        }
    }
    // Room executes all queries on a separate thread.
// Observed Flow will notify the observer when the data has changed.
    val allNotes: Flow<List<Notes>> = notesDao.getAlphabetizedNotes()
    // By default Room runs suspend queries off the main thread, therefore, we don't need to
// implement anything else to ensure we're not doing long running database work
// off the main thread.
    suspend fun insert(notes: Notes) {
        notesDao.insert(notes)
    }
    suspend fun deleteAll() {
        notesDao.deleteAll()
    }
    suspend fun deleteOne(id:Int) {
        notesDao.deleteOne(id)
    }
}