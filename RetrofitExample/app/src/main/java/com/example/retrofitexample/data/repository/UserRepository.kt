package com.example.retrofitexample.data.repository

import com.example.retrofitexample.data.mapper.toUser
import com.example.retrofitexample.data.remote.UserApiService
import com.example.retrofitexample.data.remote.dto.UserRequestDto
import com.example.retrofitexample.model.User

class UserRepository(
    private val apiService: UserApiService
) {

    suspend fun getUsers(): List<User> {
        return apiService.getUsers()
            .users
            .map { userDto -> userDto.toUser() }
    }

    suspend fun searchUsers(query: String): List<User> {
        return apiService.searchUsers(query)
            .users
            .map { userDto -> userDto.toUser() }
    }

    suspend fun createUser(
        firstName: String,
        lastName: String,
        age: Int,
        email: String
    ): User {
        val request = UserRequestDto(
            firstName = firstName,
            lastName = lastName,
            age = age,
            email = email
        )

        return apiService.createUser(request).toUser()
    }

    suspend fun updateUser(
        id: Int,
        firstName: String? = null,
        lastName: String? = null,
        age: Int? = null,
        email: String? = null
    ): User {
        val request = UserRequestDto(
            firstName = firstName,
            lastName = lastName,
            age = age,
            email = email
        )

        return apiService.updateUser(id, request).toUser()
    }

    suspend fun deleteUser(id: Int): User {
        return apiService.deleteUser(id).toUser()
    }
}