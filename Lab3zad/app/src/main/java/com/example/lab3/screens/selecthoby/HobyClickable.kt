package com.example.lab3.screens.selecthoby

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HobyClickable(
    hoby:String,
    onHobyClick:(String)->Unit
)
{
    Row(
        modifier = Modifier.padding(12.dp).fillMaxSize()
    ){
        Text(text=hoby,
            //mora zoves funkciju sa parametrima gde treba inace se ona ne izvrsi
            modifier = Modifier.clickable{onHobyClick(hoby)}
        )
    }
}