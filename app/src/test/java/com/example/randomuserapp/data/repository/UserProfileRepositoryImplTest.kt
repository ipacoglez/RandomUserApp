package com.example.randomuserapp.data.repository

import com.example.randomuserapp.data.remote.response.Coordinates
import com.example.randomuserapp.data.remote.response.Dob
import com.example.randomuserapp.data.remote.response.Id
import com.example.randomuserapp.data.remote.response.InfoDTO
import com.example.randomuserapp.data.remote.response.Location
import com.example.randomuserapp.data.remote.response.Login
import com.example.randomuserapp.data.remote.response.Name
import com.example.randomuserapp.data.remote.response.Picture
import com.example.randomuserapp.data.remote.response.Registered
import com.example.randomuserapp.data.remote.response.ResultDTO
import com.example.randomuserapp.data.remote.response.Street
import com.example.randomuserapp.data.remote.response.Timezone
import com.example.randomuserapp.data.remote.response.UserProfileResponseDTO
import com.example.randomuserapp.domain.repository.UserProfileRepository

class UserProfileRepositoryImplTest():UserProfileRepository{
    var fakeError = false
    override suspend fun getRandomUser(): UserProfileResponseDTO =
         if(fakeError){
             val list= listOf<ResultDTO>()
             UserProfileResponseDTO(
                 InfoDTO(
                     page = 0,
                     results = 0,
                     seed = "",
                     version=""
                 ),
                 results = list
             )
        } else {
            DummyUser.userProfileResponseDTO
        }
}

class DummyUser{
    companion object{

        private val dob = Dob(
            age = 0,
            date = "2000-08-31T04:20:45.265Z"
        )
        private val id = Id(
            name = "90000",
            value = ""
        )
        private val coordinates = Coordinates(
            latitude = "",
            longitude = ""
        )

        private val street = Street(
            name = "Los Angeles, CA, US.",
            number = 0
        )

        private val timezone = Timezone(
            description = "",
            offset = ""
        )
        private val location = Location(
            city = "Los Angeles",
            coordinates = coordinates,
            country = "US",
            postcode = 9000,
            state = "CA",
            street = street,
            timezone = timezone
        )
        private val name = Name(
            first = "John",
            last = "Doe",
            title = "Mr."
        )

        private val picture = Picture(
            large = "https://directemployers.org/wp-content/uploads/2018/08/avatar-JohnDoe.jpg",
            medium = "https://directemployers.org/wp-content/uploads/2018/08/avatar-JohnDoe.jpg",
            thumbnail = "https://directemployers.org/wp-content/uploads/2018/08/avatar-JohnDoe.jpg"
        )

        private val registered = Registered(
            age = 0,
            date = ""
        )

        private val login = Login(
            md5 = "",
            password = "password",
            salt = "",
            sha1 = "",
            sha256 = "",
            username = "jonhdoe",
            uuid = "212121"
        )
        private val resultDTO = ResultDTO(
            cell = "",
            dob = dob,
            email = "john@company.com",
            gender = "male",
            id = id,
            location = location,
            login = login,
            name = name,
            nat = "",
            phone = "1234567890",
            picture = picture,
            registered = registered
        )
        private val list = listOf(resultDTO, resultDTO, resultDTO)
        private val response = UserProfileResponseDTO(
            InfoDTO(
                page = 0,
                results = 0,
                seed = "",
                version = ""
            ), results = list
        )
        val userProfileResponseDTO = response
    }
}


