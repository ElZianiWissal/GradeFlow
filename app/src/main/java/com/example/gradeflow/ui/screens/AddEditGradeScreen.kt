package com.example.gradeflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gradeflow.model.Grade
import com.example.gradeflow.ui.components.PremiumButton
import com.example.gradeflow.ui.components.PremiumTextField
import com.example.gradeflow.ui.theme.Cream
import com.example.gradeflow.ui.theme.RoseGold
import com.example.gradeflow.viewmodel.GradeViewModel
import androidx.compose.runtime.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditGradeScreen(
    navController: NavController,
    gradeViewModel: GradeViewModel,
    gradeId: String?
) {
    val isEditMode = gradeId != null && gradeId != "new"

    val grades by gradeViewModel.grades.collectAsState()

    val existingGrade = grades.find {
        it.id == gradeId
    }

    var subject by remember { mutableStateOf("") }
    var score by remember { mutableStateOf("") }
    var coefficient by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }
    var scoreError by remember {
        mutableStateOf<String?>(null)
    }

    LaunchedEffect(existingGrade) {
        existingGrade?.let {
            subject = it.subject
            score = it.score.toString()
            coefficient = it.coefficient.toString()
            comment = it.comment
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isEditMode) "Modifier une note" else "Ajouter une note",
                        color = RoseGold,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Cream)
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {

            PremiumTextField(
                value = subject,
                onValueChange = { subject = it },
                label = "Matière",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            PremiumTextField(
                value = score,
                onValueChange = { score = it },
                label = "Note",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            PremiumTextField(
                value = coefficient,
                onValueChange = { coefficient = it },
                label = "Coefficient",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            PremiumTextField(
                value = comment,
                onValueChange = { comment = it },
                label = "Commentaire",
                modifier = Modifier.fillMaxWidth(),
                singleLine = false
            )

            Spacer(modifier = Modifier.height(28.dp))
            scoreError?.let {

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = it,
                    color = RoseGold
                )
            }
            PremiumButton(
                text = if (isEditMode) "Modifier" else "Ajouter",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                val scoreValue =
                    score.toDoubleOrNull() ?: -1.0

                if (scoreValue < 0 || scoreValue > 20) {

                    scoreError =
                        "La note doit être entre 0 et 20"

                    return@PremiumButton
                }

                scoreError = null

                val grade = Grade(
                    id = if (isEditMode) gradeId ?: "" else "",
                    subject = subject,
                    score = scoreValue,
                    coefficient = coefficient.toDoubleOrNull() ?: 1.0,
                    comment = comment
                )

                if (isEditMode) {
                    gradeViewModel.updateGrade(grade) {
                        navController.popBackStack()
                    }
                } else {
                    gradeViewModel.addGrade(grade) {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}