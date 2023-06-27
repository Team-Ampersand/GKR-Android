package com.mpersand.data.network.api

import com.mpersand.data.dto.request.ViolationRequest
import com.mpersand.data.dto.response.ViolationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ViolationApi {
    @GET("violation")
    suspend fun getViolationHistory(
        @Header("Authorization") accessToken: String
    ): List<ViolationResponse>

    @POST("violation")
    suspend fun violatingRental(
        @Header("Authorization") accessToken: String,
        @Body body: ViolationRequest
    )
}