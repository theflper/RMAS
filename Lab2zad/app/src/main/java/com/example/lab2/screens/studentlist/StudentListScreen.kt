package com.example.lab2.screens.studentlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab2.data.Student

@Composable
fun StudentListScreen(
    students: List<Student>,//nasa lista studenata
    onAddClick: () -> Unit,//klik na dugme
    onStudentClick: (Student) -> Unit//klik na studenta
) {
    Scaffold(
        floatingActionButton = {
            //ovde kazemo da je funkcija onClick za ovaj element
            FloatingActionButton(onClick = onAddClick) {
                Text("+")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Students",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))
            //ona ucitava kolko treba
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(students) { student ->
                    StudentRow(
                        student = student,
                        onStudentClick = onStudentClick
                    )
                }
            }
        }
    }
}