@file:OptIn(ExperimentalMaterial3Api::class)

package com.robert.notes.ui.screen.notedetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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

    Scaffold(topBar = {
        NoteTopAppBar(onBackPress = {
            navController.popBackStack()
        })
    }, floatingActionButton = {
        FloatingActionButton(modifier = Modifier
            .padding(16.dp)
            .navigationBarsPadding()
            .imePadding(), onClick = {
            viewModel.saveNote()
            navController.popBackStack()
        }) {
            Icon(imageVector = Icons.Filled.Check, contentDescription = "save")
        }
    }) { innerPadding ->
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
fun NoteTopAppBar(onBackPress: () -> (Unit)) {
    TopAppBar(
        navigationIcon = {
            Icon(
                modifier = Modifier.clickable { onBackPress() },
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "back"
            )
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = { }, modifier = Modifier.fillMaxWidth()
    )
}