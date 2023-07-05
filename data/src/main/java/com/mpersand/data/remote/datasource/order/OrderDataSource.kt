package com.mpersand.data.remote.datasource.order

import com.mpersand.data.dto.order.request.OrderRequest
import com.mpersand.data.dto.order.response.EquipmentResponse

interface OrderDataSource {
    suspend fun getEquipmentRentalByUserList(): List<EquipmentResponse>

    suspend fun postRentalRequest(orderRequest: OrderRequest)
}