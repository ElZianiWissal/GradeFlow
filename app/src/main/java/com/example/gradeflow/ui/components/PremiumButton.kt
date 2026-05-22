package com.example.gradeflow.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gradeflow.ui.theme.RoseGold
import com.example.gradeflow.ui.theme.WhiteBroken

@Composable
fun PremiumButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = RoseGold,
            contentColor = WhiteBroken
        )
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold
        )
    }
}