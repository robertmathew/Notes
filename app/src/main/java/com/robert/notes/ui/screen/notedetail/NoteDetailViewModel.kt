package com.robert.notes.ui.screen.notedetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.robert.notes.data.local.Note
import com.robert.notes.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
        val current = LocalDateTime.now().format(formatter)
        if (foundNote.value != null) {
            updateNoteDetails(
                foundNote.value!!.copy(
                    title = title,
                    note = content,
                    updatedAt = current
                )
            )
        } else {
            addNote(
                Note(
                    title = title,
                    note = content,
                    createdAt = current,
                    updatedAt = current
                )
            )
        }
    }

    fun updateNoteDetails(note: Note) {
        noteRepository.updateNote(note)
    }

    fun deleteNote() {
        if (foundNote.value != null) {
            deleteNoteDetails(foundNote.value!!)
        }
    }

    fun deleteNoteDetails(note: Note) {
        noteRepository.deleteNote(note)
    }
}