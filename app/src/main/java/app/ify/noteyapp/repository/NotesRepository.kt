package app.ify.noteyapp.repository

import androidx.lifecycle.LiveData
import app.ify.noteyapp.roomdb.Note
import app.ify.noteyapp.roomdb.NoteDao

class NotesRepository(private val noteDao: NoteDao) {

    //Getting data from the Dao
    val allNote : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insertNote(note: Note){
        return noteDao.insert(note)
    }
}