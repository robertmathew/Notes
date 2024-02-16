package com.robert.notes.data.local

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(

    @PrimaryKey(autoGenerate = true)
    @Nullable
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "note")
    var note: String,

    @ColumnInfo(name = "updatedAt")
    var updatedAt: String,

    @ColumnInfo(name = "createdAt")
    var createdAt: String,
)