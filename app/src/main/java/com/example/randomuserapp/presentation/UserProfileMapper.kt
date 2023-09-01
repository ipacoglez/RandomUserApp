package com.example.randomuserapp.presentation

import com.example.randomuserapp.data.remote.response.ResultDTO
import com.example.randomuserapp.domain.model.UserProfile

fun ResultDTO.toUserProfileDomain(): UserProfile = UserProfile(
    name = "${name.title}. ${name.first} ${name.last}, ${dob.age} YO",
    email = email,
    username = "@${login.username}",
    gender = gender,
    dob = dob.date,
    address = "#${location.street.number} ${location.street.name}, ${location.state}, ${location.city}, ${location.country}, ${location.postcode}.",
    phoneNumber = phone,
    picture = picture.medium
)