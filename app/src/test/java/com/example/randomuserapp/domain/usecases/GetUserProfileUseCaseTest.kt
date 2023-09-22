package com.example.randomuserapp.domain.usecases

import com.example.randomuserapp.data.repository.UserProfileRepositoryImplTest
import com.example.randomuserapp.data.repository.DummyUser
import com.example.randomuserapp.presentation.toUserProfileDomain
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetUserProfileUseCaseTest {
    private lateinit var getUserProfileUseCase: GetUserProfileUseCase
    private lateinit var userProfileRepositoryImplTest: UserProfileRepositoryImplTest

    @Before
    fun setup() {
        userProfileRepositoryImplTest = UserProfileRepositoryImplTest()
        getUserProfileUseCase = GetUserProfileUseCase(userProfileRepositoryImplTest)
    }

    @Test
    fun `when api return valid data then return data profile`() {
        runBlocking {
            val response = DummyUser.userProfileResponseDTO.results[0].toUserProfileDomain()

            val getUserProfileUseCase = getUserProfileUseCase.invoke().last()
            print(getUserProfileUseCase.data)
            assert(getUserProfileUseCase.data != null)
            assertEquals(response, getUserProfileUseCase.data)
        }
    }

    @Test
    fun `when api return empty list then return message error`() {
        runBlocking {
            userProfileRepositoryImplTest.fakeError = true
            val getUserProfileUseCase = getUserProfileUseCase.invoke().last()
            print(getUserProfileUseCase.data)
            assert(getUserProfileUseCase.data == null)
            assert(getUserProfileUseCase.message.equals("Empty List"))
        }
    }

}