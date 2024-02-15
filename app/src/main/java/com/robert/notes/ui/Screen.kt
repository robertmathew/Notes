package com.robert.notes.ui

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object EditScreen: Screen("edit_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { args -> append("/$args") }
        }
    }

    fun withNoteIdArgs(noteId: Int?): String {
        return buildString {
            append(route)
            append("?id=$noteId")
        }
    }
}