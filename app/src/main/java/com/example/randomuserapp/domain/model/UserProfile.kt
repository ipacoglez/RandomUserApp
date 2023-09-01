package com.example.randomuserapp.domain.model

data class UserProfile(
    val name: String,
    val email: String,
    val username: String,
    val gender:String,
    val dob: String,
    val age: String,
    val address: String,
    val phoneNumber: String,
    val picture: String
)