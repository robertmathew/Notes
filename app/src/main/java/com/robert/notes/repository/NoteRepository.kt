package com.robert.notes.repository

import androidx.lifecycle.MutableLiveData
import com.robert.notes.data.local.Note
import com.robert.notes.data.local.NoteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes = MutableLiveData<List<Note>>()
    val foundNote = MutableLiveData<Note>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addNote(newNote: Note) {
        coroutineScope.launch(Dispatchers.IO) {
            noteDao.addNote(newNote)
        }
    }

    fun updateNote(newNote: Note) {
        coroutineScope.launch(Dispatchers.IO) {
            noteDao.updateNote(newNote)
        }
    }
    fun getAllNotes() {
        coroutineScope.launch(Dispatchers.IO) {
            allNotes.postValue(noteDao.getAllNotes())
        }
    }

    fun deleteNote(note: Note) {
        coroutineScope.launch(Dispatchers.IO) {
            noteDao.deleteNote(note)
        }
    }

    fun findNoteById(noteId: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            foundNote.postValue(noteDao.findNoteById(noteId))
        }
    }


}