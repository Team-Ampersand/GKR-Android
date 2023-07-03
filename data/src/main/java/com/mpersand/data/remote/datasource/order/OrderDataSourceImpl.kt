package com.mpersand.data.remote.datasource.order

import com.mpersand.data.dto.order.request.OrderRequest
import com.mpersand.data.network.api.OrderApi
import javax.inject.Inject

class OrderDataSourceImpl @Inject constructor(
    private val orderApi: OrderApi
): OrderDataSource {
    override suspend fun postRentalRequest(orderRequest: OrderRequest) = orderApi.postRentalRequest(orderRequest)
}