package app.ify.noteyapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDB : RoomDatabase() {
    abstract val notesDao: NoteDao
    // Singleton Design Pattern
    // Only one instance of the database exists

    companion object {
        @Volatile
        private var INSTANCE: NoteDB? = null

        fun getInstance(context: Context): NoteDB? {
            // ensuring that only one thread can execute the block of code inside the synchronized block at any given time
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null){

                    // Creating the Database Object
                    var instance = Room.databaseBuilder(
                        context = context.applicationContext,
                        NoteDB::class.java,
                        "notes_db"
                    ).build()

                }
                INSTANCE = instance

                return instance
            }

        }
    }
}