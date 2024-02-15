package com.robert.notes.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.robert.notes.ui.NoteDestinationsArgs.NOTE_ID_ARG
import com.robert.notes.ui.screen.notedetail.NoteDetailScreen
import com.robert.notes.ui.screen.notes.ListScreen

object NoteDestinationsArgs {
    const val NOTE_ID_ARG = "noteId"
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            ListScreen(navController = navController)
        }
        composable(
            route = Screen.EditScreen.route + "?id={$NOTE_ID_ARG}",
            arguments = listOf(
                navArgument(NOTE_ID_ARG) { type = NavType.IntType; defaultValue = -1 },
            )
        ) { entry ->
            NoteDetailScreen(
                noteId = entry.arguments?.getInt(NOTE_ID_ARG),
                navController = navController
            )
        }
    }
}