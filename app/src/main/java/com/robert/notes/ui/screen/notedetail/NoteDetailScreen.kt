package com.robert.notes.ui.screen.notedetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.robert.notes.data.local.Note
import com.robert.notes.ui.screen.notes.NotesViewModel

@Composable
fun EditNoteScreen(
    noteId: String? = null,
    navController: NavController,
    viewModel: NoteDetailViewModel = hiltViewModel()
) {

    noteId?.toInt()?.let { viewModel.getNoteById(it) }

    val currentNote: Note? by viewModel.foundNote.observeAsState()

    currentNote?.title?.let { Text(text = it) }
}