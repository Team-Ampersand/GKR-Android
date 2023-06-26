package com.mpersand.data.network.api

import com.mpersand.data.dto.response.UserResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {
    @GET("user")
    suspend fun getUser(
        @Header("Authorization") accessToken: String
    ): UserResponse

    @DELETE("user/logout")
    suspend fun logout(
        @Header("Authorization") accessToken: String
    )
}