package com.mpersand.data.dto.request

data class RepairRequest(
    val productNumber: String,
    val reason: String,
    val description: String,
    val repairDate: String,
    val cost: Int,
    val comment: String
)
