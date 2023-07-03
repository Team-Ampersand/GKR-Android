package com.mpersand.data.remote.datasource.order

import com.mpersand.data.dto.order.request.OrderRequest
import com.mpersand.data.network.api.OrderApi
import com.mpersand.data.util.safeApiCall
import javax.inject.Inject

class OrderDataSourceImpl @Inject constructor(
    private val orderApi: OrderApi
): OrderDataSource {
    override suspend fun postRentalRequest(orderRequest: OrderRequest) = safeApiCall { orderApi.postRentalRequest(orderRequest) }
}