package com.robert.notes.ui.screen.notedetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.robert.notes.data.local.Note
import com.robert.notes.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(private val noteRepository: NoteRepository) :
    ViewModel() {

    val foundNote: LiveData<Note> = noteRepository.foundNote

    var title by mutableStateOf("")
        private set

    fun updateTitle(input: String) {
        title = input
    }

    var content by mutableStateOf("")
        private set

    fun updateContent(input: String) {
        content = input
    }

    fun getNoteById(noteId: Int) {
        noteRepository.findNoteById(noteId)
    }

    fun addNote(note: Note) {
        noteRepository.addNote(note)
    }

    fun saveNote() {
        if (foundNote.value != null) {
            updateNoteDetails(
                foundNote.value!!.copy(
                    title = title,
                    note = content
                )
            )
        } else {
            addNote(
                Note(
                    title = title,
                    note = content,
                    createdAt = "15-2-2023",
                    updatedAt = "23-10-2023",
                    id = null
                )
            )
        }
    }

    fun updateNoteDetails(note: Note) {
        noteRepository.updateNote(note)
    }
}