package com.example.lab3.screens.selecthoby

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items//ovo mora da imas da items radi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SelectHobyScreen(
    hobys: List<String>,
    onHobyClick:(String)-> Unit
)
{
    LazyColumn(
        modifier = Modifier.padding(12.dp).fillMaxWidth()
    ){
        items(hobys)
        {
            hoby->HobyClickable(hoby=hoby,onHobyClick=onHobyClick)
        }
    }
}
