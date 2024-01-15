package com.robert.notes.ui.screen.notedetail

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

    fun getNoteById(noteId: Int) {
        noteRepository.findNoteById(noteId)
    }

    fun addNote(note: Note) {
        noteRepository.addNote(note)
    }

    fun updateNoteDetails(note: Note) {
        noteRepository.updateNote(note)
    }
}