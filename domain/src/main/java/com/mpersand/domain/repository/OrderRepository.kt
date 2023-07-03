package com.mpersand.domain.repository

import com.mpersand.domain.model.order.request.OrderRequestModel

interface OrderRepository {
    suspend fun postRentalRequest(orderRequest: OrderRequestModel)
}