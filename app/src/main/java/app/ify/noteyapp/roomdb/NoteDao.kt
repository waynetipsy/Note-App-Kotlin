package app.ify.noteyapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao   //An Interface or Abstract class for defining methods for database Operation
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Query("SELECT * FROM notes_table")
    fun getAllNotes() : LiveData<List<Note>>
}