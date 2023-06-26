package com.mpersand.data.network.api

import com.mpersand.data.BuildConfig
import com.mpersand.data.dto.order.request.ExtensionRequest
import com.mpersand.data.dto.order.request.OrderRequest
import com.mpersand.data.dto.order.request.ResultRequest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OrderApiTest {
    private lateinit var retrofit: Retrofit
    private lateinit var orderApi: OrderApi
    private lateinit var accessToken: String

    @Before
    fun setUp() {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        orderApi = retrofit.create(OrderApi::class.java)
    }

    @Test
    fun `사용자가 대여한 기자재 list 조회`() {
        accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwMjhAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4NzczODU3NCwiZXhwIjoxNjg3NzM5NDc0fQ.3mURFxb3gk_zl1bkC4QN_aZP6BBl9PkHQSdVYQvSJHs"
        val response = runBlocking {
            orderApi.getEquipmentRentalByUserList(
                accessToken = accessToken,
            )
        }
        println(response)
    }

    @Test
    fun `대여 요청 list 조회`() {
        accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwMjhAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4NzczODU3NCwiZXhwIjoxNjg3NzM5NDc0fQ.3mURFxb3gk_zl1bkC4QN_aZP6BBl9PkHQSdVYQvSJHs"
        val response = runBlocking {
            orderApi.getRentalRequestList(
                accessToken = accessToken,
            )
        }
        println(response)
    }

    @Test
    fun `대기중인 요청 list 조회`() {
        accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwMjhAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4NzczODU3NCwiZXhwIjoxNjg3NzM5NDc0fQ.3mURFxb3gk_zl1bkC4QN_aZP6BBl9PkHQSdVYQvSJHs"
        val response = runBlocking {
            orderApi.getWaitRequestList(
                accessToken = accessToken,
            )
        }
        println(response)
    }

    @Test
    fun `반납 안 한 학생 list 조회`() {
        accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwMjhAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4NzczODU3NCwiZXhwIjoxNjg3NzM5NDc0fQ.3mURFxb3gk_zl1bkC4QN_aZP6BBl9PkHQSdVYQvSJHs"
        val response = runBlocking {
            orderApi.getNoReturnUserList(
                accessToken = accessToken,
            )
        }
        println(response)
    }

    @Test
    fun `요청 결과 반환`() {
        accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwMjhAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4NzczODU3NCwiZXhwIjoxNjg3NzM5NDc0fQ.3mURFxb3gk_zl1bkC4QN_aZP6BBl9PkHQSdVYQvSJHs"
        val resultRequest = ResultRequest(
            equipmentId = "2021맥북002",
            decision =  "RENTAL_ACCEPT"
        )
        val response = runBlocking {
            orderApi.returnRequestResult(
                accessToken = accessToken,
                resultRequest =  resultRequest
            )
        }
        println(response)
    }

    @Test
    fun `대여 요청 등록`() {
        accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwMjhAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4NzczOTcwMiwiZXhwIjoxNjg3NzQwNjAyfQ.FWB-zYsyT01cElpKjUlskBGbtwsXfKgKDzHIROptTl4"
        val orderRequest = OrderRequest(
            equipmentId = "2021맥북020",
            reason = "Test",
            state = "RENTAL_STATE",
            decision = "RENTAL_ACCEPT"
        )
        val response = runBlocking {
            orderApi.postRentalRequest(
                accessToken = accessToken,
                orderRequest =  orderRequest
            )
        }
        println(response)
    }

    @Test
    fun `반납 요청 등록`() {
        accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwMjhAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4NzczODU3NCwiZXhwIjoxNjg3NzM5NDc0fQ.3mURFxb3gk_zl1bkC4QN_aZP6BBl9PkHQSdVYQvSJHs"
        val orderRequest = OrderRequest(
            equipmentId = "2021맥북002",
            reason = "Test",
            state = "RENTAL_STATE",
            decision = "RENTAL_ACCEPT"
        )
        val response = runBlocking {
            orderApi.postReturnRequest(
                accessToken = accessToken,
                orderRequest =  orderRequest
            )
        }
        println(response)
    }

    @Test
    fun `연장 요청 등록`() {
        accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwMjhAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4NzczODU3NCwiZXhwIjoxNjg3NzM5NDc0fQ.3mURFxb3gk_zl1bkC4QN_aZP6BBl9PkHQSdVYQvSJHs"
        val extensionRequest = ExtensionRequest(
            equipmentId = "2021맥북020"
        )
        val response = runBlocking {
            orderApi.postExtensionRequest(
                accessToken = accessToken,
                extensionRequest = extensionRequest
            )
        }
        println(response)
    }
}