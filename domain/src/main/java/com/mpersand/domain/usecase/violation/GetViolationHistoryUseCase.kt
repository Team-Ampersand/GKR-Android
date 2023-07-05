package com.mpersand.domain.usecase.violation

import com.mpersand.domain.repository.ViolationRepository
import javax.inject.Inject

class GetViolationHistoryUseCase @Inject constructor(
    private val violationRepository: ViolationRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        violationRepository.getViolationHistory()
    }
}