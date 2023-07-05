package com.mpersand.data.repository

import com.mpersand.data.dto.order.request.asOrderRequest
import com.mpersand.data.dto.order.response.asEquipmentResponseModel
import com.mpersand.data.remote.datasource.order.OrderDataSource
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.domain.model.order.request.OrderRequestModel
import com.mpersand.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderDataSource: OrderDataSource
): OrderRepository {
    override suspend fun getEquipmentRentalByUserList(): List<EquipmentResponseModel> = orderDataSource.getEquipmentRentalByUserList()
        .map { it.asEquipmentResponseModel() }

    override suspend fun postRentalRequest(orderRequest: OrderRequestModel) = orderDataSource.postRentalRequest(orderRequest.asOrderRequest())
}