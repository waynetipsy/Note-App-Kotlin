package app.ify.noteyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModelProvider
import app.ify.noteyapp.repository.NotesRepository
import app.ify.noteyapp.roomdb.Note
import app.ify.noteyapp.roomdb.NoteDB
import app.ify.noteyapp.screens.DisplayNotesList
import app.ify.noteyapp.ui.theme.NoteyAppTheme
import app.ify.noteyapp.viewmodel.NoteViewModel
import app.ify.noteyapp.viewmodel.NoteViewModelFactory
import kotlin.jvm.java

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Room DB
        val database = NoteDB.getInstance(applicationContext)

        //Repository
        val repository =  NotesRepository(database.notesDao)

        // ViewModel Factory
        val viewModelFactory = NoteViewModelFactory(repository)

        //ViewModel
        val noteViewModel = ViewModelProvider(
           this,
            viewModelFactory
        )[NoteViewModel::class.java]



        setContent {
            NoteyAppTheme {
                val note1 = Note(0, "This is a demo", "Welcome my friends, Please rate us 5 stars" +
                        "to continue updating this course...",
                    "f59597".toColorInt()
                )
                // Inserting Note into ROOM DB
                noteViewModel.insert(note1)

                //Display All Records in ROOM DB
                // Observing LiveData From a ViewModel
                // & getting its state in a composable fun
                val notes by noteViewModel
                    .allNotes.observeAsState(emptyList())
                // .observeAsState: Converts a LiveData
                // into a State Object that can be observed
                // within a composable fun

                DisplayNotesList(notes = notes)


            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteyAppTheme {
        Greeting("Android")
    }
}