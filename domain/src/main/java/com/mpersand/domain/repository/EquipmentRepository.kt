package com.mpersand.domain.repository

import com.mpersand.domain.model.equipment.response.EquipmentResponseModel

interface EquipmentRepository {
    suspend fun getAllEquipments(): List<EquipmentResponseModel>
}