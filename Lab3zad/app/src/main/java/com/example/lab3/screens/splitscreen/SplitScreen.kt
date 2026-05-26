package com.example.lab3.screens.splitscreen

import androidx.compose.runtime.Composable
import com.example.lab3.data.Exam
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

//ekran sa dugmicima za dodavanje ekrana i hobija
@Composable
fun SplitScreen(
    goBack: () -> Unit,
    gotoHoby: ()->Unit,
    gotoExam: ()->Unit,
) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    )
    {
        Button(
            onClick = gotoHoby,
            modifier = Modifier.fillMaxWidth(),
        )
        {
            Text("Add Hoby")
        }
        Button(
            onClick = gotoExam,
            modifier = Modifier.fillMaxWidth(),
        )
        {
            Text("Add Exam")
        }
        Button(
            onClick = goBack,
            modifier = Modifier.fillMaxWidth(),
        )
        {
            Text("Go back")
        }
    }
}