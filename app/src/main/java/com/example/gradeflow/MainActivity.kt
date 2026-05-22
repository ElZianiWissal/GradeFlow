package com.example.gradeflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.gradeflow.navigation.AppNavigation
import com.example.gradeflow.ui.theme.GradeFlowTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            GradeFlowTheme {

                AppNavigation()

            }
        }
    }
}