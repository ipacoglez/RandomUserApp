package com.example.randomuserapp.data.remote

import com.example.randomuserapp.data.remote.response.UserProfileResponseDTO
import retrofit2.http.GET

interface RandomUserApiClient {
    @GET("api")
    suspend fun getRandomUser(): UserProfileResponseDTO
}