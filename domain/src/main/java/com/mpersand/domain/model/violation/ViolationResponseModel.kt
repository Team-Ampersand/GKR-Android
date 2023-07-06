package com.mpersand.domain.model.violation

import java.util.UUID

data class ViolationResponseModel(
    val userId: UUID,
    val reason: String,
    val date: String
)
