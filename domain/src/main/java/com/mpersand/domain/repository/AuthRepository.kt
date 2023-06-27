package com.mpersand.domain.repository

import com.mpersand.domain.model.request.SignInRequestModel
import com.mpersand.domain.model.response.SignInResponseModel

interface AuthRepository {
    suspend fun signIn(signInRequestModel: SignInRequestModel): SignInResponseModel
}