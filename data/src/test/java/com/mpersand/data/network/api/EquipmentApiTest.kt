package com.mpersand.data.network.api

import com.mpersand.data.BuildConfig
import com.mpersand.data.dto.equipment.request.EquipmentRequest
import com.mpersand.data.dto.equipment.request.ModifyEquipmentRequest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EquipmentApiTest {
    private lateinit var retrofit: Retrofit
    private lateinit var equipmentApi: EquipmentApi
    private lateinit var accessToken: String

    @Before
    fun setUp() {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        equipmentApi = retrofit.create(EquipmentApi::class.java)
    }

    @Test
    fun `모든 기자재 리스트`() {
        accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwNDlAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4Nzc1NjE5OCwiZXhwIjoxNjg3NzU3MDk4fQ.1lciPvAi5jBYRDd9l3qqIU2v9EBJfS_OjjCMIh-Cnok"
        val response = runBlocking {
            equipmentApi.getAllEquipments(
                accessToken = accessToken
            )
        }

        println(response)
    }

    @Test
    fun `기자재 등록`() {
        accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwNDlAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4Nzc1NjE5OCwiZXhwIjoxNjg3NzU3MDk4fQ.1lciPvAi5jBYRDd9l3qqIU2v9EBJfS_OjjCMIh-Cnok"
        runBlocking {
            equipmentApi.registerEquipment(
                accessToken = accessToken,
                body = EquipmentRequest(
                    productNumber = "2021-맥북-002",
                    name = "맥북",
                    image = "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/refurb-mbp16touch-silver-gallery-2019_GEO_KR?wid=572&hei=572&fmt=jpeg&qlt=95&.v=1582233078186",
                    description = "macbook pro2"
                )
            )
        }
    }

    @Test
    fun `대여하지 않은 기자재 목록`() {
        accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwNDlAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4Nzc1NjE5OCwiZXhwIjoxNjg3NzU3MDk4fQ.1lciPvAi5jBYRDd9l3qqIU2v9EBJfS_OjjCMIh-Cnok"
        val response = runBlocking {
            equipmentApi.getNotRentEquipment(
                accessToken = accessToken
            )
        }

        println(response)
    }

    @Test
    fun `대여한 기자재 목록`() {
        accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwNDlAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4Nzc1NjE5OCwiZXhwIjoxNjg3NzU3MDk4fQ.1lciPvAi5jBYRDd9l3qqIU2v9EBJfS_OjjCMIh-Cnok"
        val response = runBlocking {
            equipmentApi.getIsRentEquipment(
                accessToken = accessToken
            )
        }

        println(response)
    }

    @Test
    fun `기자재 정보`() {
        accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwNDlAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4Nzc1NjE5OCwiZXhwIjoxNjg3NzU3MDk4fQ.1lciPvAi5jBYRDd9l3qqIU2v9EBJfS_OjjCMIh-Cnok"
        val response = runBlocking {
            equipmentApi.getEquipmentInfo(
                accessToken = accessToken,
                productNumber = "00000-00001"
            )
        }

        println(response)
    }

    @Test
    fun `기자재 수정`() {
        accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwNDlAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4Nzc1NjE5OCwiZXhwIjoxNjg3NzU3MDk4fQ.1lciPvAi5jBYRDd9l3qqIU2v9EBJfS_OjjCMIh-Cnok"
        runBlocking {
            equipmentApi.modifyEquipment(
                accessToken = accessToken,
                productNumber = "2021-맥북-003",
                body = ModifyEquipmentRequest(
                    name = "macbook macbook2",
                    image = "https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/refurb-mbp16touch-silver-gallery-2019_GEO_KR?wid=572&hei=572&fmt=jpeg&qlt=95&.v=1582233078186",
                    description = "macbook pro intel"
                )
            )
        }
    }

    @Test
    fun `기자재 삭제`() {
        accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwNDlAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4Nzc1NjE5OCwiZXhwIjoxNjg3NzU3MDk4fQ.1lciPvAi5jBYRDd9l3qqIU2v9EBJfS_OjjCMIh-Cnok"
        val response = runBlocking {
            equipmentApi.deleteEquipment(
                accessToken = accessToken,
                productNumber = "2021-맥북-003"
            )
        }

        println(response)
    }

    @Test
    fun `기자재 필터`() {
        accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzMjEwNDlAZ3NtLmhzLmtyIiwiY2xpZW50SWQiOiJiYzAxY2U3MjUzNDY0MjBlOTRlZGQ3NTYwNzFmNmUxZWFhZWQyOWMwOTEwMTRjMDFiZDcyOTZjYjFjNzc3ZWYzIiwidHlwZSI6ImFjY2VzcyIsImlhdCI6MTY4Nzc1NjE5OCwiZXhwIjoxNjg3NzU3MDk4fQ.1lciPvAi5jBYRDd9l3qqIU2v9EBJfS_OjjCMIh-Cnok"
        val response = runBlocking {
            equipmentApi.equipmentFilter(
                accessToken = accessToken,
                name = "맥북"
            )
        }

        println(response)
    }
}