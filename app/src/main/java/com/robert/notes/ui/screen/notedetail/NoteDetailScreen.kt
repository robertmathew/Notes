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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.robert.notes.data.local.Note

@Composable
fun NoteDetailScreen(
    noteId: Int? = null,
    navController: NavController,
    viewModel: NoteDetailViewModel = hiltViewModel()
) {

    noteId?.let { if (it != -1) viewModel.getNoteById(it) }

    val currentNote: Note? by viewModel.foundNote.observeAsState()

    currentNote?.let { note ->
        viewModel.updateTitle(note.title)
        viewModel.updateContent(note.note)
    }

    Scaffold(
        topBar = {
            NoteTopAppBar(onBackPress = {
                navController.popBackStack()
            }, onSavePress = {
                viewModel.saveNote()
                navController.popBackStack()
            }, onDeletePress = {
                viewModel.deleteNote()
                navController.popBackStack()
            })
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
                textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                value = viewModel.title,
                label = { Text(text = "Title") },
                onValueChange = { title -> viewModel.updateTitle(title) })
            TextField(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
                value = viewModel.content,
                label = { Text(text = "Enter your content") },
                onValueChange = { content -> viewModel.updateContent(content) })
        }
    }
}

@Composable
fun NoteTopAppBar(onBackPress: () -> (Unit), onSavePress: () -> Unit, onDeletePress: () -> Unit) {
    TopAppBar(navigationIcon = {
        Icon(
            modifier = Modifier.clickable { onBackPress() },
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "back"
        )
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.primary,
    ), title = { }, modifier = Modifier.fillMaxWidth(), actions = {
        IconButton(onClick = { onDeletePress() }) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
        }
        IconButton(onClick = { onSavePress() }) {
            Icon(imageVector = Icons.Filled.Check, contentDescription = "Save")
        }
    })
}