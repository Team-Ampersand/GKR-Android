package com.mpersand.data.network.api

import com.mpersand.data.BuildConfig
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserApiTest {
    private lateinit var retrofit: Retrofit
    private lateinit var userApi: UserApi

    @Before
    fun setUp() {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        userApi = retrofit.create(UserApi::class.java)
    }

    @Test
    fun `Access token을 통해 유저 정보를 가져옵니다`() {
        val accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwMzVAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4Nzc0OTExNSwiZXhwIjoxNjg3NzUwMDE1fQ.dTk1FuXbagsJb1XMOW4DJSV-xjrpQ5EaJFi6gXktN4Y"
        val response = runBlocking {
            userApi.getUser(accessToken)
        }
        println(response)
    }

    @Test
    fun `Access token을 통해 유저 로그아웃 처리를 진행합니다`() {
        val accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwMzVAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4Nzc0OTExNSwiZXhwIjoxNjg3NzUwMDE1fQ.dTk1FuXbagsJb1XMOW4DJSV-xjrpQ5EaJFi6gXktN4Y"
        runBlocking {
            userApi.logout(accessToken)
        }
    }
}
