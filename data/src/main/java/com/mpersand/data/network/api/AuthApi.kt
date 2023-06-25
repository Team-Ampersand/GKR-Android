package com.mpersand.data.network.api

import com.mpersand.data.dto.request.SignInRequest
import com.mpersand.data.dto.response.ReissueTokenResponse
import com.mpersand.data.dto.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/signin")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): SignInResponse

    @PATCH("auth/reissue")
    suspend fun reissueToken(
        @Header("Refresh-token") refreshToken: String
    ): ReissueTokenResponse
}