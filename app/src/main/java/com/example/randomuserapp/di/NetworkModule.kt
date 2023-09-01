package com.example.randomuserapp.di

import com.example.randomuserapp.common.Constants
import com.example.randomuserapp.data.remote.RandomUserApiClient
import com.example.randomuserapp.data.repository.UserProfileRepositoryImpl
import com.example.randomuserapp.domain.repository.UserProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRandomUserApi(): RandomUserApiClient {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesRandomUserRepository(randomUserApiClient: RandomUserApiClient): UserProfileRepository =
        UserProfileRepositoryImpl(randomUserApiClient)
}