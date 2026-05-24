package com.example.lab2zadatak.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorUI() {
    Surface {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            CalculatorDisplay(modifier = Modifier.fillMaxWidth())
            CalculatorKeyboard(modifier = Modifier)
            Spacer(modifier = Modifier.weight(0.9f))
            ElevatedButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(24.dp)) {
                Text(text = "Go to history")
            }
        }
    }
}

@Composable
fun CalculatorDisplay(modifier: Modifier) {
    Card(shape = MaterialTheme.shapes.large, modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp)) {
        Text(text = "0", // viewModel.displayValue je namenjen za ovo
            modifier = Modifier.padding(24.dp).align(Alignment.CenterHorizontally).fillMaxWidth(),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.displaySmall)
    }
}

@Composable
fun CalculatorKeyboard(modifier: Modifier) {
    Text(text = "Grid dugmica kalkulatora ovde.")
}