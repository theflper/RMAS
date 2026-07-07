package com.example.retrofitexample.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserRequestDto(
    val firstName: String? = null,
    val lastName: String? = null,
    val age: Int? = null,
    val email: String? = null
)