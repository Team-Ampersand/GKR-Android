package com.mpersand.data.dto.order.request

import com.mpersand.domain.model.order.request.OrderRequestModel

data class OrderRequest(
    val equipmentId: String,
    val reason: String,
    val state: String,
    val decision: String
)

fun OrderRequestModel.asOrderRequest() = OrderRequest(
    equipmentId = equipmentId,
    reason = reason,
    state = state,
    decision = decision
)