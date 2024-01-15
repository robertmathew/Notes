package com.robert.notes.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.robert.notes.ui.NoteDestinationsArgs.NOTE_ID_ARG
import com.robert.notes.ui.screen.notedetail.EditNoteScreen
import com.robert.notes.ui.screen.notes.ListScreen

object NoteDestinationsArgs {
    const val USER_MESSAGE_ARG = "userMessage"
    const val NOTE_ID_ARG = "noteId"
    const val TITLE_ARG = "title"
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            ListScreen(navController = navController)
        }
        composable(
            route = Screen.EditScreen.route + "/{$NOTE_ID_ARG}",
            arguments = listOf(
                navArgument(NOTE_ID_ARG) { type = NavType.StringType; defaultValue = "0" },
            )
        ) { entry ->
            EditNoteScreen(
                noteId = entry.arguments?.getString(NOTE_ID_ARG),
                navController = navController
            )
        }
    }
}