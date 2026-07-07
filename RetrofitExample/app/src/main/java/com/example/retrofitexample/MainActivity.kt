package com.example.retrofitexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.retrofitexample.ui.theme.RetrofitExampleTheme
import com.example.retrofitexample.users.UsersScreen
import com.example.retrofitexample.users.UsersViewModel
import com.example.retrofitexample.users.UsersViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel: UsersViewModel by viewModels {
        val app = application as UsersApplication

        UsersViewModelFactory(
            userRepository = app.userRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UsersScreen(
                viewModel = viewModel
            )
        }
    }
}