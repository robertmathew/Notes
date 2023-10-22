package com.robert.notes

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.robert.notes.data.local.Note
import com.robert.notes.ui.theme.NotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: MainViewModel, modifier: Modifier = Modifier) {
//    viewModel.addNote(Note(title = "Title", note = "Note", createdAt = "21-10-2023", updatedAt = "21-10-2023", id = null))

    viewModel.getAllNotes()
    val lazyListState = rememberLazyListState()

    val noteList: List<Note> by viewModel.noteList.observeAsState(initial = listOf())

    LazyColumn(
        modifier = Modifier.padding(vertical = 4.dp),
        state = lazyListState
    ) {
        items(items = noteList) { note ->
            Text(text = note.note)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesTheme {

    }
}