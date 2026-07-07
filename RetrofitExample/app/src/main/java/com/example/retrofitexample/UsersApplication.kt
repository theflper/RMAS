package com.example.retrofitexample

import android.app.Application
import com.example.retrofitexample.data.remote.UserApiService
import com.example.retrofitexample.data.repository.UserRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class UsersApplication : Application() {

    lateinit var userRepository: UserRepository
        private set

    override fun onCreate() {
        super.onCreate()

        val json = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()

        val userApiService = retrofit.create(UserApiService::class.java)

        userRepository = UserRepository(userApiService)
    }
}