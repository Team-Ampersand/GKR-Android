package com.mpersand.data.remote.datasource.equipment

import com.mpersand.data.dto.order.response.EquipmentResponse

interface EquipmentDataSource {

    suspend fun getAllEquipments(): List<EquipmentResponse>
}