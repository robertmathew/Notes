package com.robert.notes.di

import android.content.Context
import androidx.room.Room
import com.robert.notes.data.NoteRoomDatabase
import com.robert.notes.data.local.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideNoteDao(appDatabase: NoteRoomDatabase): NoteDao {
        return appDatabase.noteDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): NoteRoomDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NoteRoomDatabase::class.java,
            "appDB"
        ).build()
    }

}