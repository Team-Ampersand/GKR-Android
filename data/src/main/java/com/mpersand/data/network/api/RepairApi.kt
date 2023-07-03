package com.mpersand.data.network.api

import com.mpersand.data.dto.repair.request.RepairRequest
import com.mpersand.data.dto.repair.response.RepairResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface RepairApi {
    @POST("repair")
    suspend fun postRepair(
        @Body body: RepairRequest
    )

    @GET("repair")
    suspend fun getRepair(
        @Query("productNumber") productNumber: String
    ): RepairResponse

    @PATCH("repair")
    suspend fun updateRepair(
        @Body body: RepairRequest
    )

    @DELETE("repair")
    suspend fun deleteRepair(
        @Query("productNumber") productNumber: String
    )
}