package com.mpersand.data.dto.order.response

import com.mpersand.domain.model.response.EquipmentResponseModel

data class EquipmentResponse(
    val productNumber: String,
    val name: String,
    val image: String,
    val description: String,
    val rentStatus: String
)

fun EquipmentResponse.asEquipmentResponseImpl() = EquipmentResponseModel(
    productNumber = productNumber,
    name = name,
    image = image,
    description = description,
    rentStatus = rentStatus
)
