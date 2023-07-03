package com.mpersand.domain.usecase.user

import com.mpersand.domain.repository.UserRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = kotlin.runCatching { userRepository.logout() }
}