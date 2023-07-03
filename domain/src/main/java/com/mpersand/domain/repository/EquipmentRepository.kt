package com.mpersand.domain.repository

import com.mpersand.domain.model.response.EquipmentResponseModel

interface EquipmentRepository {
    suspend fun getAllEquipments(): List<EquipmentResponseModel>
}