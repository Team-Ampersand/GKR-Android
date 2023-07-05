package com.mpersand.data.remote.datasource.violation

import com.mpersand.data.dto.violation.response.ViolationResponse

interface ViolationDataSource {
    suspend fun getViolationHistory(): List<ViolationResponse>
}