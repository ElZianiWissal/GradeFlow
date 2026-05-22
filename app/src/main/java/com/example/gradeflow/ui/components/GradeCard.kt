package com.example.gradeflow.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gradeflow.model.Grade
import com.example.gradeflow.ui.theme.CardColor
import com.example.gradeflow.ui.theme.DarkText
import com.example.gradeflow.ui.theme.RoseGold
import androidx.compose.foundation.clickable
import androidx.compose.ui.unit.sp

@Composable
fun GradeCard(
    grade: Grade,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Column {

                Text(
                    text = grade.subject,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = RoseGold
                )

                Text(
                    text = "Note : ${grade.score}",
                    color = DarkText
                )

                Text(
                    text = "Coefficient : ${grade.coefficient}",
                    color = DarkText
                )
                Text(
                    text = "✏\uFE0F Taper pour modifier",
                    color = RoseGold.copy(alpha = 0.7f),
                    fontSize = 12.sp
                )
                if (grade.comment.isNotEmpty()) {

                    Text(
                        text = grade.comment,
                        color = DarkText
                    )
                }
            }

            IconButton(
                onClick = onDelete
            ) {

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = RoseGold
                )
            }
        }
    }
}