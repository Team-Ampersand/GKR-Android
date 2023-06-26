package com.mpersand.data.dto.response

import java.util.UUID

data class ViolationResponse(
    val userId: UUID,
    val reason: String,
    val date: String
)
