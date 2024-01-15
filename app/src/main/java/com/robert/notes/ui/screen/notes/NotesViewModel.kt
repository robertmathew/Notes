package com.robert.notes.ui.screen.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.robert.notes.data.local.Note
import com.robert.notes.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val noteRepository: NoteRepository) :
    ViewModel() {

    val noteList: LiveData<List<Note>> = noteRepository.allNotes

    val foundNote: LiveData<Note> = noteRepository.foundNote

    fun getAllNotes() {
        noteRepository.getAllNotes()
    }

    fun addNote(note: Note) {
        noteRepository.addNote(note)
        getAllNotes()
    }

    fun updateNoteDetails(note: Note) {
        noteRepository.updateNote(note)
        getAllNotes()
    }
}