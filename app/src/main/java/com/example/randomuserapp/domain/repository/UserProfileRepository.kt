package com.example.randomuserapp.domain.repository

import com.example.randomuserapp.data.remote.response.UserProfileResponseDTO

interface UserProfileRepository {
    suspend fun getRandomUser(): UserProfileResponseDTO
}