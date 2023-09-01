package com.example.randomuserapp.data.repository

import com.example.randomuserapp.data.remote.RandomUserApiClient
import com.example.randomuserapp.data.remote.response.UserProfileResponseDTO
import com.example.randomuserapp.domain.repository.UserProfileRepository
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val api: RandomUserApiClient
): UserProfileRepository {
    override suspend fun getRandomUser(): UserProfileResponseDTO {
        return api.getRandomUser()
    }
}