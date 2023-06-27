package com.mpersand.data.repository

import com.mpersand.data.dto.auth.request.asSignInRequest
import com.mpersand.data.dto.auth.response.asSignInResponseModel
import com.mpersand.data.local.datasource.LocalDataSource
import com.mpersand.data.remote.datasource.auth.AuthDataSource
import com.mpersand.domain.model.request.SignInRequestModel
import com.mpersand.domain.model.response.SignInResponseModel
import com.mpersand.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val authDataSource: AuthDataSource
): AuthRepository {
    override suspend fun signIn(signInRequestModel: SignInRequestModel): SignInResponseModel =
        authDataSource.signIn(signInRequestModel.asSignInRequest()).asSignInResponseModel()

    override suspend fun saveToken(accessToken: String, refreshToken: String, accessTokenExp: String, refreshTokenExp: String) {
        localDataSource.saveToken(
            accessToken = accessToken,
            refreshToken = refreshToken,
            accessTokenExp = accessTokenExp,
            refreshTokenExp = refreshTokenExp
        )
    }
}