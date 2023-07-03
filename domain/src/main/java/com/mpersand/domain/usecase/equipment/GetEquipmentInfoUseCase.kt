package com.mpersand.domain.usecase.equipment

import com.mpersand.domain.repository.EquipmentRepository
import javax.inject.Inject

class GetEquipmentInfoUseCase @Inject constructor(
    private val equipmentRepository: EquipmentRepository
) {
    suspend operator fun invoke(productNumber: String) = equipmentRepository.getEquipmentInfo(productNumber)
}