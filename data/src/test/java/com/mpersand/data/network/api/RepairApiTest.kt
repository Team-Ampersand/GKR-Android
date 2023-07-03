package com.mpersand.data.network.api

import com.mpersand.data.BuildConfig
import com.mpersand.data.dto.repair.request.RepairRequest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepairApiTest {
    private lateinit var retrofit: Retrofit
    private lateinit var repairApi: RepairApi

    @Before
    fun setUp() {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        repairApi = retrofit.create(RepairApi::class.java)
    }

    @Test
    fun `수리 내역을 등록합니다`() {
        val accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwMzVAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4Nzc1NjE4NiwiZXhwIjoxNjg3NzU3MDg2fQ.d_fRJd3vD2T2dow6jqVweKgYZFtWxOOXX91R4TUroFs"
        val body = RepairRequest(
            productNumber = "2021맥북002",
            reason = "힌지 손상",
            description = "힌지를 새 부품으로 교체",
            repairDate = "2023-06-26",
            cost = 500000,
            comment = ""
        )
        runBlocking {
            repairApi.postRepair(
                accessToken = accessToken,
                body = body
            )
        }
    }

    @Test
    fun `수리 내역을 갱신 합니다`() {
        val accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwMzVAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4Nzc1NjE4NiwiZXhwIjoxNjg3NzU3MDg2fQ.d_fRJd3vD2T2dow6jqVweKgYZFtWxOOXX91R4TUroFs"
        val body = RepairRequest(
            productNumber = "2021맥북002",
            reason = "힌지 손상",
            description = "힌지를 새 부품으로 교체",
            repairDate = "2023-06-26",
            cost = 100000,
            comment = ""
        )
        runBlocking {
            repairApi.updateRepair(
                accessToken = accessToken,
                body = body
            )
        }
    }

    @Test
    fun `기자재 id를 통해 수리 내역을 가져옵니다`() {
        val accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwMzVAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4Nzc1NjE4NiwiZXhwIjoxNjg3NzU3MDg2fQ.d_fRJd3vD2T2dow6jqVweKgYZFtWxOOXX91R4TUroFs"
        val productNumber = "2021맥북002"
        val repair = runBlocking {
            repairApi.getRepair(
                accessToken = accessToken,
                productNumber = productNumber
            )
        }
        println(repair)
    }

    @Test
    fun `수리 내역을 삭제 합니다`() {
        val accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwMzVAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4Nzc1NjE4NiwiZXhwIjoxNjg3NzU3MDg2fQ.d_fRJd3vD2T2dow6jqVweKgYZFtWxOOXX91R4TUroFs"
        val productNumber = "2021맥북002"
        runBlocking {
            repairApi.deleteRepair(
                accessToken = accessToken,
                productNumber = productNumber
            )
        }
    }
}
