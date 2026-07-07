package com.example.retrofitexample.model

data class User(
    val id: Int,
    val fullName: String,
    val age: Int,
    val email: String,
    val imageUrl: String?
)