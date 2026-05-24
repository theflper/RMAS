package com.example.lab3.screens.addexam

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
import com.example.lab3.data.Exam
import com.example.lab3.screens.addexam.ExamRow

@Composable
fun AddExamScreen(
    exams: List<Exam>,
    onSaveExam: (String) -> Unit,
    onBackClick: () -> Unit
) {
    var examName by remember { mutableStateOf("") }
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
            value = examName,
            onValueChange = {
                examName = it
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
                if (examName.isNotBlank()) {
                    onSaveExam(examName.trim())
                    examName = "" // Resetujemo polje nakon uspešnog unosa
                } else {
                    isError = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Exam")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Added exams",
            style = MaterialTheme.typography.titleMedium
        )

        if (exams.isEmpty()) {
            Text("No exams added.")
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(exams) { exam ->
                    ExamRow(exam = exam)
                }
            }
        }

        OutlinedButton(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}