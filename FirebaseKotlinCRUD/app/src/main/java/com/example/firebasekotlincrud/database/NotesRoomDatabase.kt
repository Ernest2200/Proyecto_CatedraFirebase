package com.example.firebasekotlincrud.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firebasekotlincrud.dao.NotesDao
import com.example.firebasekotlincrud.entities.Notes

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesRoomDatabase :RoomDatabase() {
    abstract fun notesDao(): NotesDao
    companion object {
        // Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: NotesRoomDatabase? = null
        fun getDatabase(context: Context): NotesRoomDatabase{
// if the INSTANCE is not null, then return it,
// if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesRoomDatabase::class.java,
                    "notes_database"
                ).build()
                INSTANCE = instance
// return instance
                instance
            }
        }
    }
}