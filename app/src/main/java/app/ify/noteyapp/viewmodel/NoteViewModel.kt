package app.ify.noteyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.ify.noteyapp.repository.NotesRepository
import app.ify.noteyapp.roomdb.Note
import kotlinx.coroutines.launch

// View Model: store & Manage Ui-related Data
// separating the UI-related logic from
// UI controller (composable/Activity/Frag.)

// ViewModel prepares and manages data for the UI

class NoteViewModel(private val repository: NotesRepository)
    : ViewModel() {

        val allNotes: LiveData<List<Note>> =
            repository.allNotes
    // ViewModelScope: is a coroutine scope tied to the
    // ViewModel's Lifecycle, Ensuring that any coroutines
    // launched within it are automatically canceled if
    // the viewModel is cleared

    fun insert(note: Note) =
        viewModelScope.launch {  //Coroutine Scope
            repository.insertNote(note)
        }
}