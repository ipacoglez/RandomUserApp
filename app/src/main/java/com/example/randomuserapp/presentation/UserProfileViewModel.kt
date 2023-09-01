package com.example.randomuserapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuserapp.common.Resource
import com.example.randomuserapp.domain.model.UserProfileState
import com.example.randomuserapp.domain.usecases.GetUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val getUserProfileUseCase: GetUserProfileUseCase
): ViewModel() {

    private val _userProfile: MutableLiveData<UserProfileState> = MutableLiveData()
    val userProfile get() = _userProfile

    fun getUserProfile() {
        getUserProfileUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _userProfile.value = UserProfileState(isLoading = true)
                }
                is  Resource.Success -> {
                    _userProfile.value = UserProfileState(isLoading = false, data = result.data)
                }
                is Resource.Error -> {
                    _userProfile.value = UserProfileState(
                        isLoading = false, error = result.message ?: "Unexpected error"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}