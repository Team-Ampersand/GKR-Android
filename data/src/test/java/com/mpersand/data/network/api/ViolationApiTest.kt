package com.mpersand.data.network.api

import com.mpersand.data.BuildConfig
import com.mpersand.data.dto.request.ViolationRequest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID

class ViolationApiTest {
    private lateinit var retrofit: Retrofit
    private lateinit var violationApi: ViolationApi

    @Before
    fun setUp() {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        violationApi = retrofit.create(ViolationApi::class.java)
    }

    @Test
    fun `Access token으로 유저 제재 내역을 확인합니다`() {
        val accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwMzVAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4Nzc1Nzk0MywiZXhwIjoxNjg3NzU4ODQzfQ.bSzWRQUTOf3IfDEy6lEVHnK0YCKd6LkB61Gr_M_nZ_Y"
        val response = runBlocking {
            violationApi.getViolationHistory(accessToken)
        }
        println(response)
    }

    @Test
    fun `userId를 통해 유저를 제재합니다`() {
        val accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwMzVAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4Nzc1Nzk0MywiZXhwIjoxNjg3NzU4ODQzfQ.bSzWRQUTOf3IfDEy6lEVHnK0YCKd6LkB61Gr_M_nZ_Y"
        val body = ViolationRequest(
            userId = UUID.fromString("b1151dca-5119-48ea-88b1-c5dacd762bf5"),
            reason = "기자재 대여 연체",
            date = "2023-06-27"
        )
        runBlocking {
            violationApi.violatingRental(
                accessToken = accessToken,
                body = body
            )
        }
    }
}
