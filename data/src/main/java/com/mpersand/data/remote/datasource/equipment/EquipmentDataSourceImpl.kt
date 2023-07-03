package com.mpersand.data.remote.datasource.equipment

import com.mpersand.data.dto.order.response.EquipmentResponse
import com.mpersand.data.network.api.EquipmentApi
import com.mpersand.data.util.safeApiCall
import javax.inject.Inject

class EquipmentDataSourceImpl @Inject constructor(
    private val equipmentApi: EquipmentApi
): EquipmentDataSource {
    override suspend fun getAllEquipments(): List<EquipmentResponse> = safeApiCall { equipmentApi.getAllEquipments() }
    override suspend fun getEquipmentInfo(productNumber: String): EquipmentResponse = safeApiCall { equipmentApi.getEquipmentInfo(productNumber) }
}