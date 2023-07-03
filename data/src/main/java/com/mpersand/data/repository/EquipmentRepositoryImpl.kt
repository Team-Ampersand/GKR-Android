package com.mpersand.data.repository

import com.mpersand.data.dto.order.response.asEquipmentResponseImpl
import com.mpersand.data.remote.datasource.equipment.EquipmentDataSource
import com.mpersand.domain.model.response.EquipmentResponseModel
import com.mpersand.domain.repository.EquipmentRepository
import javax.inject.Inject

class EquipmentRepositoryImpl @Inject constructor(
    private val equipmentDataSource: EquipmentDataSource
): EquipmentRepository {
    override suspend fun getAllEquipments(): List<EquipmentResponseModel> = equipmentDataSource.getAllEquipments().map { it.asEquipmentResponseImpl() }
}