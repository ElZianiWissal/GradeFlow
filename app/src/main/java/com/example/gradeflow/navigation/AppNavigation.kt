package com.example.gradeflow.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gradeflow.ui.screens.HomeScreen
import com.example.gradeflow.ui.screens.LoginScreen
import com.example.gradeflow.ui.screens.RegisterScreen
import com.example.gradeflow.viewmodel.AuthViewModel
import com.example.gradeflow.viewmodel.GradeViewModel
import com.example.gradeflow.ui.screens.AddEditGradeScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val authViewModel: AuthViewModel = viewModel()

    val gradeViewModel: GradeViewModel = viewModel()

    val startDestination =
        if (authViewModel.isLoggedIn()) {
            Routes.Home.route
        } else {
            Routes.Login.route
        }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.Login.route) {
            LoginScreen(navController, authViewModel)
        }

        composable(Routes.Register.route) {
            RegisterScreen(navController, authViewModel)
        }

        composable(Routes.Home.route) {
            HomeScreen(
                navController,
                authViewModel,
                gradeViewModel
            )
        }

        composable(
            route = Routes.AddEditGrade.route,
            arguments = listOf(
                navArgument("gradeId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val gradeId =
                backStackEntry.arguments?.getString("gradeId")

            AddEditGradeScreen(
                navController = navController,
                gradeViewModel = gradeViewModel,
                gradeId = gradeId
            )
        }
    }
}