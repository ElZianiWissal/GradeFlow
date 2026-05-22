package com.example.gradeflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gradeflow.navigation.Routes
import com.example.gradeflow.ui.components.PremiumButton
import com.example.gradeflow.ui.components.PremiumTextField
import com.example.gradeflow.ui.theme.Cream
import com.example.gradeflow.ui.theme.DarkText
import com.example.gradeflow.ui.theme.RoseGold
import com.example.gradeflow.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {

    var email by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }

    val loading by authViewModel.loading.collectAsState()

    val error by authViewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .padding(28.dp),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Créer un compte",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = RoseGold
        )

        Spacer(modifier = Modifier.height(32.dp))

        PremiumTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = "Email",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        PremiumTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = "Mot de passe",
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (error != null) {

            Text(
                text = error ?: "",
                color = RoseGold,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        if (loading) {

            CircularProgressIndicator(color = RoseGold)

        } else {

            PremiumButton(
                text = "Créer un compte",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
            ) {

                authViewModel.register(
                    email = email,
                    password = password
                ) {

                    navController.navigate(Routes.Home.route) {

                        popUpTo(Routes.Register.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        TextButton(
            onClick = {
                navController.popBackStack()
            }
        ) {

            Text(
                text = "Déjà un compte ?",
                color = DarkText
            )
        }
    }
}