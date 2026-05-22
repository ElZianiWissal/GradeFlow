package com.example.gradeflow.navigation

sealed class Routes(val route: String) {

    object Login : Routes("login")

    object Register : Routes("register")

    object Home : Routes("home")

    object AddEditGrade : Routes("add_edit_grade/{gradeId}") {

        fun createRoute(gradeId: String) =
            "add_edit_grade/$gradeId"
    }
}