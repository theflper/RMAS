package com.example.lab3.screens.addstudent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.lab3.data.Exam

@Composable
fun AddStudentScreen(
    onSaveStudent: (
        studentNumber: String,
        firstName: String,
        lastName: String,
        yearOfAdmission: Int,
        faculty: String,
        city:String,
        street:String,
        number:String
    ) -> Unit,
    onBackClick: () -> Unit
) {
    var studentNumber by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var yearOfAdmission by remember { mutableStateOf("") }
    var faculty by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var facultyList by remember {mutableStateOf(
        listOf(
            "ELFAK NIS",
            "ETF BG",
            "FTN NS"
        )
    )
    }//lista fakulteta na koje mozemo da idemo
    var expanded by remember { mutableStateOf(false) }
    //da li je otvorena drop down lista
    val isFormValid =
        studentNumber.isNotBlank() &&
                firstName.isNotBlank() &&
                lastName.isNotBlank() &&
                yearOfAdmission.toIntOrNull() != null


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Add student",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = studentNumber,
            onValueChange = { studentNumber = it },
            label = { Text("Student number") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = yearOfAdmission,
            onValueChange = { yearOfAdmission = it },
            label = { Text("Year of admission") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("City") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = street,
            onValueChange = { street = it },
            label = { Text("Street") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = number,
            onValueChange = { number = it },
            label = { Text("Number") },
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Choose faculty",
            style = MaterialTheme.typography.titleMedium
        )
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = if (faculty.isNotEmpty()) faculty else "Choose faculty")
                //koristi dodelu kod if-a ovde ako faculty nije "" onda se
                //dodeli faculcy ako ne onda ispise da treba da odabermo fakultet
                //Text(text = faculty.ifEmpty { "Choose faculty" })
                //ovo iznad je drugi nacin da zamenimo if
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                // Prolazimo kroz filtriranu listu dostupnih ispita
                facultyList.forEach { selfaculty ->
                    DropdownMenuItem(
                        text = { Text(selfaculty) },
                        onClick = {
                            faculty = selfaculty
                            expanded = false
                        }
                    )
                }
            }
        }
        Button(
            onClick = {
                onSaveStudent(
                    studentNumber,
                    firstName,
                    lastName,
                    yearOfAdmission.toInt(),
                    faculty,
                    city,
                    street,
                    number
                )
            },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save student")
        }

        OutlinedButton(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}