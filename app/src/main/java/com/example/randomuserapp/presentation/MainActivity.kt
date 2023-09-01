package com.example.randomuserapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.randomuserapp.R
import com.example.randomuserapp.common.Constants
import com.example.randomuserapp.common.loadImage
import com.example.randomuserapp.common.parseDate
import com.example.randomuserapp.databinding.ActivityMainBinding
import com.example.randomuserapp.domain.model.UserProfile
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val userProfileViewModel: UserProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userProfileViewModel.getUserProfile()
        observeUserProfile()

        binding.updateButton.setOnClickListener {
            userProfileViewModel.getUserProfile()
        }
    }

    private fun observeUserProfile() {
        userProfileViewModel.userProfile.observe(this) { state ->

            setVisibleLoading(state.isLoading)
            setError(state.error)

            state.data?.let { setSuccess(it) }
        }
    }

    private fun setSuccess(data: UserProfile) {
        binding.apply {
            with(data) {
                setHideLoading()
                containerAddress.isVisible = true
                containerInfo.isVisible = true
                containerOtherInfo.isVisible = true
                nameText.text = name
                usernameText.text = username
                addressText.text = address
                ageText.text = age
                birthdayText.text = dob.parseDate()
                phoneText.text = phoneNumber

                pictureImage.loadImage(picture)

                setGender(gender)
            }
        }
    }

    private fun setError(errorMessage: String) {
        if (errorMessage.isNotEmpty()) {
            setHideLoading()
            Snackbar.make(
                binding.root,
                errorMessage,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun setVisibleLoading(isLoading: Boolean) {
        if (isLoading) binding.progressLoading.isVisible = true
    }

    private fun setHideLoading() {
        binding.progressLoading.isGone = true
    }

    private fun setGender(gender: String) {
        binding.apply {
            genderTex.text = gender
            if (gender == Constants.GENDER_FEMALE)
                genderTex
                    .setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_female, 0, 0, 0)
            else
                genderTex
                    .setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_male, 0, 0, 0)
        }
    }

}