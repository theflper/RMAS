package com.example.lab2.screens.studentDetails
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab2.data.Student
@Composable
fun StudentDetailsScreen(student:Student,onCancel: () -> Unit) {
    //element ispod elementa u koloni
    Column(
        //ovo je fiksno samo copy paste od postojecih komponenti
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement =  Arrangement.spacedBy(12.dp)
    ) {
            Text(
                text = "Detalji o studentu",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp,top=24.dp)
            )
            Text(
                text = "Index: "+student.studentNumber,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Ime: "+student.firstName,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Prezime: "+student.lastName,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Prosecna ocena: "+student.avgGrade.toString(),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Godina upisa: "+student.yearOfAdmission.toString(),
                style = MaterialTheme.typography.headlineMedium
            )
        OutlinedButton(
            onClick = onCancel,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}