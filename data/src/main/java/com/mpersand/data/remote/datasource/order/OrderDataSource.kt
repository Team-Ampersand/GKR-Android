package com.mpersand.data.remote.datasource.order

import com.mpersand.data.dto.order.request.OrderRequest

interface OrderDataSource {
    suspend fun postRentalRequest(orderRequest: OrderRequest)
}