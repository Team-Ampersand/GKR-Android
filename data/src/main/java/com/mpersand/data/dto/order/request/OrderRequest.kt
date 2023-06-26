package com.mpersand.data.dto.order.request

data class OrderRequest(
    val equipmentId: String,
    val reason: String,
    val state: String,
    val decision: String
)
