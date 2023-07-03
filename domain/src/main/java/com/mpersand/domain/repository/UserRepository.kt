package com.mpersand.domain.repository

import com.mpersand.domain.model.response.UserResponseModel

interface UserRepository {
    suspend fun getUser(): UserResponseModel

    suspend fun logout()
}