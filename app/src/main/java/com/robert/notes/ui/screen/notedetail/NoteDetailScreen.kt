@file:OptIn(ExperimentalMaterial3Api::class)

package com.robert.notes.ui.screen.notedetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.robert.notes.data.local.Note

@Composable
fun EditNoteScreen(
    noteId: String? = null,
    navController: NavController,
    viewModel: NoteDetailViewModel = hiltViewModel()
) {

    noteId?.toInt()?.let { viewModel.getNoteById(it) }

    val currentNote: Note? by viewModel.foundNote.observeAsState()

    currentNote?.let { note ->
        Scaffold(topBar = { NoteTopAppBar(noteTitle = note.title, navController = navController) },
            floatingActionButton = {
                FloatingActionButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "save")
                }
            }) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(modifier = Modifier.padding(8.dp), text = note.note)
            }

        }
    }
}

@Composable
fun NoteTopAppBar(noteTitle: String, navController: NavController) {
    TopAppBar(
        navigationIcon = {
            Icon(
                modifier = Modifier.clickable { navController.popBackStack() },
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "back"
            )
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = { Text(text = noteTitle) }, modifier = Modifier.fillMaxWidth()
    )
}