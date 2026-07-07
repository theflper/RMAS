package com.example.retrofitexample.data.mapper

import com.example.retrofitexample.data.remote.dto.UserDto
import com.example.retrofitexample.model.User

fun UserDto.toUser(): User {
    return User(
        id = id,
        fullName = "$firstName $lastName",
        age = age,
        email = email,
        imageUrl = image
    )
}