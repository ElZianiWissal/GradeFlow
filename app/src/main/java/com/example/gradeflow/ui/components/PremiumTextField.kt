package com.example.gradeflow.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.gradeflow.ui.theme.DarkText
import com.example.gradeflow.ui.theme.RoseGold
import com.example.gradeflow.ui.theme.WhiteBroken

@Composable
fun PremiumTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(label)
        },
        modifier = modifier,
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(18.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = WhiteBroken,
            unfocusedContainerColor = WhiteBroken,
            focusedIndicatorColor = RoseGold,
            unfocusedIndicatorColor = RoseGold.copy(alpha = 0.4f),
            focusedLabelColor = RoseGold,
            unfocusedLabelColor = DarkText,
            cursorColor = RoseGold
        )
    )
}