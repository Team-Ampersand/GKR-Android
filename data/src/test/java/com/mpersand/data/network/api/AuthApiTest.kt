package com.mpersand.data.network.api

import com.mpersand.data.BuildConfig
import com.mpersand.data.dto.request.SignInRequest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthApiTest {
    private lateinit var retrofit: Retrofit
    private lateinit var authApi: AuthApi

    @Before
    fun setUp() {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        authApi = retrofit.create(AuthApi::class.java)
    }

    @Test
    fun `GAuth code 값을 받아 토큰을 반환합니다`() {
        val signInRequest = SignInRequest(code = "e968179a-76d4-47d6-b6cb-3cc16d1064ff")
        val response = runBlocking {
            authApi.signIn(signInRequest = signInRequest)
        }
        println(response)
    }

    @Test
    fun `Refresh token을 통해 토큰을 재발급 받습니다`() {
        val refreshToken =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwMzVAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6InJlZnJlc2giLCJpYXQiOjE2ODc0OTAyMjgsImV4cCI6MTY4ODA5NTAyOH0.tEKGp4XmipVQBBtCWNHa2CZXMVtQcw5Xa1NhgiwmSdI"
        val response = runBlocking {
            authApi.reissueToken(refreshToken = refreshToken)
        }
        println(response)
    }
}