package com.mpersand.domain.repository

import com.mpersand.domain.model.violation.ViolationResponseModel

interface ViolationRepository {
    suspend fun getViolationHistory(): List<ViolationResponseModel>
}