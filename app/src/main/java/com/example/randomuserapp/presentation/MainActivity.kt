package com.example.randomuserapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.randomuserapp.R
import com.example.randomuserapp.common.Constants
import com.example.randomuserapp.common.loadImage
import com.example.randomuserapp.common.parseDate
import com.example.randomuserapp.databinding.ActivityMainBinding
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observeUserProfile() {
        userProfileViewModel.userProfile.observe(this) { state ->
            if (state.error.isNotEmpty()) {
                Snackbar.make(
                    binding.root,
                    state.error,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            if (state.data != null) {
                binding.apply {
                    with(state.data) {
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
        }
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