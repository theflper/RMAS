package com.example.retrofitexample.data.remote

import com.example.retrofitexample.data.remote.dto.UserDto
import com.example.retrofitexample.data.remote.dto.UserRequestDto
import com.example.retrofitexample.data.remote.dto.UsersResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {

    @GET("users")
    suspend fun getUsers(
        @Query("limit") limit: Int = 20,
        @Query("skip") skip: Int = 0
    ): UsersResponseDto

    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: Int
    ): UserDto

    @GET("users/search")
    suspend fun searchUsers(
        @Query("q") query: String
    ): UsersResponseDto

    @POST("users/add")
    suspend fun createUser(
        @Body request: UserRequestDto
    ): UserDto

    @PATCH("users/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body request: UserRequestDto
    ): UserDto

    @DELETE("users/{id}")
    suspend fun deleteUser(
        @Path("id") id: Int
    ): UserDto
}