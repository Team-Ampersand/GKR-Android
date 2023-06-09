package com.mpersand.data.repository

import com.mpersand.data.dto.order.response.asEquipmentResponseModel
import com.mpersand.data.remote.datasource.equipment.EquipmentDataSource
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.domain.repository.EquipmentRepository
import javax.inject.Inject

class EquipmentRepositoryImpl @Inject constructor(
    private val equipmentDataSource: EquipmentDataSource
): EquipmentRepository {
    override suspend fun getAllEquipments(): List<EquipmentResponseModel> = equipmentDataSource.getAllEquipments().map { it.asEquipmentResponseModel() }
    override suspend fun getEquipmentInfo(productNumber: String): EquipmentResponseModel = equipmentDataSource.getEquipmentInfo(productNumber).asEquipmentResponseModel()
    override suspend fun equipmentFilter(name: String): List<EquipmentResponseModel> = equipmentDataSource.equipmentFilter(name).map { it.asEquipmentResponseModel() }
}