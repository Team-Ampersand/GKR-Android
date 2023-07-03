package com.mpersand.domain.model.order.request

data class OrderRequestModel(
    val equipmentId: String,
    val reason: String,
    val state: String,
    val decision: String
)

