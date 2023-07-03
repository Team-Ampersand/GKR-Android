package com.mpersand.data.network.api

import com.mpersand.data.dto.equipment.request.EquipmentRequest
import com.mpersand.data.dto.equipment.request.ModifyEquipmentRequest
import com.mpersand.data.dto.order.response.EquipmentResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface EquipmentApi {
    @GET("/equipment/all")
    suspend fun getAllEquipments(): List<EquipmentResponse>

    @POST("/equipment")
    suspend fun registerEquipment(
        @Body body: EquipmentRequest
    )

    @GET("/equipment/notrent")
    suspend fun getNotRentEquipment(): List<EquipmentResponse>

    @GET("/equipment/isrent")
    suspend fun getIsRentEquipment(): List<EquipmentResponse>

    @GET("/equipment/{productNumber}")
    suspend fun getEquipmentInfo(
        @Path("productNumber") productNumber: String
    ): EquipmentResponse

    @PATCH("/equipment/{productNumber}")
    suspend fun modifyEquipment(
        @Path("productNumber") productNumber: String,
        @Body body: ModifyEquipmentRequest
    ): Response<Unit>

    @DELETE("/equipment/{productNumber}")
    suspend fun deleteEquipment(
        @Path("productNumber") productNumber: String
    ): Response<Unit>

    @GET("/equipment")
    suspend fun equipmentFilter(
        @Query("name") name: String
    ): List<EquipmentResponse>
}