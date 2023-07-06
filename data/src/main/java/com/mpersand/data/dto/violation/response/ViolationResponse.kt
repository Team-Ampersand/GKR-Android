package com.mpersand.data.dto.violation.response

import com.mpersand.domain.model.violation.ViolationResponseModel
import java.util.UUID

data class ViolationResponse(
    val userId: UUID,
    val reason: String,
    val date: String
)

fun ViolationResponse.asViolationResponseModel() = ViolationResponseModel(
    userId = userId,
    reason = reason,
    date = date
)