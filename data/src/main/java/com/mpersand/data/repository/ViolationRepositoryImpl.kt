package com.mpersand.data.repository

import com.mpersand.data.dto.violation.response.asViolationResponseModel
import com.mpersand.data.remote.datasource.violation.ViolationDataSource
import com.mpersand.domain.model.violation.ViolationResponseModel
import com.mpersand.domain.repository.ViolationRepository
import javax.inject.Inject

class ViolationRepositoryImpl @Inject constructor(
    private val violationDataSource: ViolationDataSource
): ViolationRepository {
    override suspend fun getViolationHistory(): List<ViolationResponseModel> = violationDataSource.getViolationHistory()
        .map { it.asViolationResponseModel() }
}