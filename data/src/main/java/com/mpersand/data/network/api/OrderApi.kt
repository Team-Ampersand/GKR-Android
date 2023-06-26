package com.mpersand.data.network.api

import com.mpersand.data.dto.order.request.ExtensionRequest
import com.mpersand.data.dto.order.request.OrderRequest
import com.mpersand.data.dto.order.request.ResultRequest
import com.mpersand.data.dto.order.response.EquipmentResponse
import com.mpersand.data.dto.order.response.OrderResponse
import com.mpersand.data.dto.order.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface OrderApi {
    @GET("order")
    suspend fun getEquipmentRentalByUserList(
        @Header("Authorization") accessToken: String
    ): List<EquipmentResponse>

    @GET("order/rental")
    suspend fun getRentalRequestList(
        @Header("Authorization") accessToken: String
    ): List<OrderResponse>

    @GET("order/wait")
    suspend fun getWaitRequestList(
        @Header("Authorization") accessToken: String
    ): List<OrderResponse>

    @GET("order/noreturn")
    suspend fun getNoReturnUserList(
        @Header("Authorization") accessToken: String
    ): List<UserResponse>

    @POST("order")
    suspend fun returnRequestResult(
        @Header("Authorization") accessToken: String,
        @Body resultRequest: ResultRequest
    )

    @POST("order/rental")
    suspend fun postRentalRequest(
        @Header("Authorization") accessToken: String,
        @Body orderRequest: OrderRequest
    )

    @POST("order/return")
    suspend fun postReturnRequest(
        @Header("Authorization") accessToken: String,
        @Body orderRequest: OrderRequest
    )

    @POST("order/extension")
    suspend fun postExtensionRequest(
        @Header("Authorization") accessToken: String,
        @Body extensionRequest: ExtensionRequest
    )
}