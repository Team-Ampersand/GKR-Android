package com.mpersand.data.remote.datasource.auth

import com.mpersand.data.dto.request.SignInRequest
import com.mpersand.data.dto.response.SignInResponse

interface AuthDataSource {
    suspend fun signIn(signInRequest: SignInRequest): SignInResponse
}