package com.example.randomuserapp.domain.model

data class UserProfileState(
    val isLoading: Boolean = false,
    val data: UserProfile? = null,
    val error: String = ""
)
