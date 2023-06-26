package com.mpersand.data.dto.order.response

import java.util.UUID

data class UserResponse(
    val id: UUID,
    val name: String,
    val grade: Int?,
    val classNum: Int?,
    val number: Int?
)
