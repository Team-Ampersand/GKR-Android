package com.mpersand.data.dto.request

import java.util.UUID

data class ViolationRequest(
    val userId: UUID,
    val reason: String,
    val date: String
)
