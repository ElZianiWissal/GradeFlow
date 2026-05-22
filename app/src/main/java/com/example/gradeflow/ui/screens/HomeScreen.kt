package com.example.gradeflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gradeflow.navigation.Routes
import com.example.gradeflow.ui.components.GradeCard
import com.example.gradeflow.ui.theme.Cream
import com.example.gradeflow.ui.theme.DarkText
import com.example.gradeflow.ui.theme.RoseGold
import com.example.gradeflow.viewmodel.AuthViewModel
import com.example.gradeflow.viewmodel.GradeViewModel
import androidx.compose.runtime.LaunchedEffect


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    gradeViewModel: GradeViewModel
) {

    val grades by gradeViewModel.grades.collectAsState()
    LaunchedEffect(Unit) {
        gradeViewModel.observeGrades()
    }
    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "GradeFlow",
                        color = RoseGold,
                        fontWeight = FontWeight.Bold
                    )
                },

                actions = {

                    IconButton(
                        onClick = {

                            authViewModel.logout()

                            navController.navigate(
                                Routes.Login.route
                            ) {

                                popUpTo(0)
                            }
                        }
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.ExitToApp,
                            contentDescription = null,
                            tint = RoseGold
                        )
                    }
                }
            )
        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        Routes.AddEditGrade.createRoute("new")
                    )
                },
                containerColor = RoseGold
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Cream)
                .padding(paddingValues)
                .padding(18.dp)
        ) {

            Text(
                text = "Moyenne générale",
                color = DarkText
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "%.2f".format(
                    gradeViewModel.average
                ),
                color = RoseGold,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(
                verticalArrangement =
                    Arrangement.spacedBy(14.dp),

                contentPadding =
                    PaddingValues(bottom = 80.dp)
            ) {

                items(grades) { grade ->

                    GradeCard(
                        grade = grade,

                        onDelete = {

                            gradeViewModel.deleteGrade(
                                grade.id
                            )
                        },

                        onClick = {

                            navController.navigate(
                                Routes.AddEditGrade
                                    .createRoute(grade.id)
                            )
                        }
                    )
                }
            }
        }
    }
}