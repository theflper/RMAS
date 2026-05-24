package com.example.lab2.screens.addstudent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab2.data.Student

@Composable
fun AddStudentScreen(
    onSaveStudent: (Student) -> Unit,
    //prosledjena funkcija sta se desava kad klikenemo Add student
    onCancel: () -> Unit
    //prosledjena funkcija sta se desava kad kliknemo Cancel
) {
    var studentNumber by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var avgGrade by remember { mutableDoubleStateOf(8.00) }//default vrednost
    var yearOfAdmission by remember { mutableIntStateOf(value = 2023) }//default vrednost
    //za int i double ide i tip u imenu i treba biblioteka za to
    //samo napisi i onda alt+shift+enter i predlozi ti biblioteke
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Add Student",
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
        //posto nije string display a posle cuvamo double vrednost
        OutlinedTextField(
            value = avgGrade.toString(),
            onValueChange = { avgGrade = it.toDouble() },
            label = { Text("avgGrade") },
            modifier = Modifier.fillMaxWidth()
        )
        //isto za godinu upisa samo sto je ona int
        OutlinedTextField(
            value = yearOfAdmission.toString(),
            onValueChange = { yearOfAdmission = it.toInt() },
            label = { Text("avgGrade") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val student = Student(
                    studentNumber = studentNumber,
                    firstName = firstName,
                    lastName = lastName,
                    avgGrade=avgGrade,
                    yearOfAdmission=yearOfAdmission
                )

                onSaveStudent(student)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = studentNumber.isNotBlank() &&
                    firstName.isNotBlank() &&
                    lastName.isNotBlank() &&
                    avgGrade>=6.00 && avgGrade<=10.00//uslov za prosek je dodat
                    && yearOfAdmission>2000 && yearOfAdmission<2027//uslov za godinu upisa
        ) {
            Text("Add Student")
        }

        OutlinedButton(
            onClick = onCancel,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}