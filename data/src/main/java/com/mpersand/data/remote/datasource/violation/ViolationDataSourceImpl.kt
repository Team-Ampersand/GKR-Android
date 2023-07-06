package com.mpersand.data.remote.datasource.violation

import com.mpersand.data.dto.violation.response.ViolationResponse
import com.mpersand.data.network.api.ViolationApi
import com.mpersand.data.util.safeApiCall
import javax.inject.Inject

class ViolationDataSourceImpl @Inject constructor(
    private val violationApi: ViolationApi
): ViolationDataSource {
    override suspend fun getViolationHistory(): List<ViolationResponse> = safeApiCall { violationApi.getViolationHistory() }
}