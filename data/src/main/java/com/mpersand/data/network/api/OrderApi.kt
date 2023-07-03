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
    suspend fun getEquipmentRentalByUserList(): List<EquipmentResponse>

    @GET("order/rental")
    suspend fun getRentalRequestList(): List<OrderResponse>

    @GET("order/wait")
    suspend fun getWaitRequestList(): List<OrderResponse>

    @GET("order/noreturn")
    suspend fun getNoReturnUserList(): List<UserResponse>

    @POST("order")
    suspend fun returnRequestResult(
        @Body resultRequest: ResultRequest
    )

    @POST("order/rental")
    suspend fun postRentalRequest(
        @Body orderRequest: OrderRequest
    )

    @POST("order/return")
    suspend fun postReturnRequest(
        @Body orderRequest: OrderRequest
    )

    @POST("order/extension")
    suspend fun postExtensionRequest(
        @Body extensionRequest: ExtensionRequest
    )
}