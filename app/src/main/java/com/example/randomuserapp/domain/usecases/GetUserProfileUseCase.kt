package com.example.randomuserapp.domain.usecases

import com.example.randomuserapp.common.Resource
import com.example.randomuserapp.domain.model.UserProfile
import com.example.randomuserapp.domain.repository.UserProfileRepository
import com.example.randomuserapp.presentation.toUserProfileDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(private val api: UserProfileRepository){
     operator fun invoke(): Flow<Resource<UserProfile>> = flow {
        emit(Resource.Loading())
        val response = api.getRandomUser()
        if (response.results.isNotEmpty()) {
            emit(Resource.Success(response.results[0].toUserProfileDomain()))
        } else {
            emit(Resource.Error("Empty List"))
        }
    }.catch {
        emit(Resource.Error("Unexpected error :(. Try Again."))
    }
}