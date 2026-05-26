package com.example.lab3.screens.addhoby

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddHobyScreen(
    hobyes: List<String>,
    onSaveHoby: (String) -> Unit,
    onBackClick: () -> Unit,
    onMainScreen: ()->Unit
) {
    var hobyName by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Exams",
            style = MaterialTheme.typography.headlineMedium
        )

        // Polje za unos naziva ispita
        OutlinedTextField(
            value = hobyName,
            onValueChange = {
                hobyName = it
                if (it.isNotBlank()) isError = false
            },
            label = { Text("Exam Name") },
            modifier = Modifier.fillMaxWidth(),
            isError = isError,
            supportingText = {
                if (isError) {
                    Text("Exam name cannot be empty!")
                }
            }
        )

        // Dugme za čuvanje koje poziva funkciju prosleđenu iz NavHost-a
        Button(
            onClick = {
                if (hobyName.isNotBlank()) {
                    onSaveHoby(hobyName.trim())
                    hobyName = "" // Resetujemo polje nakon uspešnog unosa
                } else {
                    isError = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled=hobyName.isNotBlank()
        ) {
            Text("Save Hoby")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Added hobys",
            style = MaterialTheme.typography.titleMedium
        )

        if (hobyes.isEmpty()) {
            Text("No exams added.")
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(hobyes) { hoby ->
                    Text(text=hoby)
                }
            }
        }
        OutlinedButton(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
        OutlinedButton(
            onClick = onMainScreen,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Main Screen")
        }
    }
}