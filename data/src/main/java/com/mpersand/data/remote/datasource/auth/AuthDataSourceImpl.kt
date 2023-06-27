package com.mpersand.data.remote.datasource.auth

import com.mpersand.data.dto.request.SignInRequest
import com.mpersand.data.dto.response.SignInResponse
import com.mpersand.data.network.api.AuthApi
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authApi: AuthApi
): AuthDataSource {
    override suspend fun signIn(signInRequest: SignInRequest): SignInResponse = authApi.signIn(signInRequest = signInRequest)
}