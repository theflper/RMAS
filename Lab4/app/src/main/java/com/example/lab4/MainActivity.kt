package com.example.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab4.navigation.StudentNavHost
import com.example.lab4.ui.theme.Lab4Theme
import kotlin.getValue

class MainActivity : ComponentActivity() {

    private val studentViewModel: StudentViewModel by viewModels {
        StudentViewModelFactory((application as StudentApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab4Theme {
                StudentNavHost(
                    studentViewModel = studentViewModel
                )
            }
        }
    }
}