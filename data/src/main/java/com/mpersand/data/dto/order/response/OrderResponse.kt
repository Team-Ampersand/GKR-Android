package com.mpersand.data.dto.order.response

import java.util.UUID

data class OrderResponse(
    val userId: UUID,
    val equipmentId: String,
    val reason: String,
    val state: String,
    val rentalDate: String,
    val returnDate: String
)
