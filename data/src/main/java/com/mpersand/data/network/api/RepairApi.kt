package com.mpersand.data.network.api

import com.mpersand.data.dto.request.RepairRequest
import com.mpersand.data.dto.response.RepairResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface RepairApi {
    @POST("repair")
    suspend fun postRepair(
        @Header("Authorization") accessToken: String,
        @Body body: RepairRequest
    )

    @GET("repair")
    suspend fun getRepair(
        @Header("Authorization") accessToken: String,
        @Query("productNumber") productNumber: String
    ): RepairResponse

    @PATCH("repair")
    suspend fun updateRepair(
        @Header("Authorization") accessToken: String,
        @Body body: RepairRequest
    )

    @DELETE("repair")
    suspend fun deleteRepair(
        @Header("Authorization") accessToken: String,
        @Query("productNumber") productNumber: String
    )
}