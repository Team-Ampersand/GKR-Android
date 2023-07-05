package com.mpersand.domain.repository

import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.domain.model.order.request.OrderRequestModel

interface OrderRepository {
    suspend fun getEquipmentRentalByUserList(): List<EquipmentResponseModel>

    suspend fun postRentalRequest(orderRequest: OrderRequestModel)
}