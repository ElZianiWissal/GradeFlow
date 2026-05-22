package com.example.gradeflow.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val GradeFlowColors = lightColorScheme(

    primary = RoseGold,

    secondary = SoftPink,

    background = Cream,

    surface = WhiteBroken,

    onPrimary = WhiteBroken,

    onSecondary = DarkText,

    onBackground = DarkText,

    onSurface = DarkText
)

@Composable
fun GradeFlowTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = GradeFlowColors,
        typography = Typography,
        content = content
    )
}