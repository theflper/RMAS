package com.example.datastoreexample.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datastoreexample.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel
) {
    val settings by viewModel.settings.collectAsState()
    // Ovo stanje sada služi samo za privremeni unos u TextField dok korisnik kuca
    var usernameInput by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "User Settings",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        // 1. INPUT I BUTTON: Spakovani u Row da stoje jedno pored drugog
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = usernameInput,
                onValueChange = { usernameInput = it },
                label = { Text("New Username") },
                modifier = Modifier.weight(1f) // TextField zauzima sav preostali prostor
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (usernameInput.isNotBlank()) {
                        viewModel.onAddNameClicked(usernameInput) // Šaljemo u ViewModel da doda u listu
                        usernameInput = "" // Brišemo tekst iz polja nakon klika
                    }
                }
            ) {
                Text("Add")
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Bold Text")
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = settings.boldText,
                onCheckedChange = { isChecked ->
                    viewModel.onBoldTextChange(isChecked)
                }
            )
        }

        Text("Font size: ${settings.fontSize}")

        Slider(
            value = settings.fontSize.toFloat(),
            onValueChange = {
                viewModel.onFontSizeChange(it.toInt())
            },
            valueRange = 12f..30f,
            steps = 17
        )

        // Tvoj preview tekst koji prikazuje trenutno SELEKTOVANO ime iz DataStore-a
        Text(
            text = "Preview text for ${settings.username.ifBlank { "Guest" }}",
            fontSize = settings.fontSize.sp, //fontsize na osnovu selektovanog
            //ovo ispod je da li je boldiran ili ne
            fontWeight = if (settings.boldText) FontWeight.Bold else FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Saved Users:", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

        // 2. LAZY COLUMN: Prikaz svih imena iz istorije
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // Uzima preostali prostor na dnu ekrana
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(settings.allNames) { name ->
                // Proveravamo da li je ovo ime trenutno selektovano u preview-u
                val isSelected = name == settings.username

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.onUsernameChange(name) // Na klik postavljamo ovo ime kao aktivno
                        },
                    colors = CardDefaults.cardColors(
                        // Ako je selektovano, obojimo ga primarnom bojom teme radi lepšeg UI-ja
                        containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
                        else MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Text(
                        text = name,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
    }
}