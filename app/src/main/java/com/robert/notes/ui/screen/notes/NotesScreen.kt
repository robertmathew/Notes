@file:OptIn(ExperimentalMaterial3Api::class)

package com.robert.notes.ui.screen.notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.robert.notes.R
import com.robert.notes.data.local.Note
import com.robert.notes.ui.Screen

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel(),
) {

    viewModel.getAllNotes()
    val lazyListState = rememberLazyListState()

    val noteList: List<Note> by viewModel.noteList.observeAsState(initial = listOf())

    Scaffold(topBar = { NoteTopAppBar() }, floatingActionButton = {
        FloatingActionButton(onClick = {
            navController.navigate(Screen.EditScreen.route)
        }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
        }
    }) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            state = lazyListState
        ) {
            items(items = noteList) { note ->
                ListItem(note = note,
                    onNoteClick = { note -> navController.navigate(Screen.EditScreen.withNoteIdArgs(note.id)) })
            }
        }
    }
}

@Composable
fun NoteTopAppBar() {
    TopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = { Text(text = stringResource(id = R.string.app_name)) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ListItem(note: Note, onNoteClick: (Note) -> Unit) {
    Column(modifier = Modifier.clickable { onNoteClick(note) }) {
        Text(
            text = note.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 8.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = note.note,
            modifier = Modifier.padding(top = 4.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}