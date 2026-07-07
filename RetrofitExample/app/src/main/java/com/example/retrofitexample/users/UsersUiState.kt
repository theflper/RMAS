package com.example.retrofitexample.users

import com.example.retrofitexample.model.User

data class UsersUiState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val errorMessage: String? = null,
    val successMessage: String? = null
)