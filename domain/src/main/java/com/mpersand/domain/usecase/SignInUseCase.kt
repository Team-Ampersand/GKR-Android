package com.mpersand.domain.usecase

import com.mpersand.domain.model.request.SignInRequestModel
import com.mpersand.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(signInRequestModel: SignInRequestModel) = kotlin.runCatching { authRepository.signIn(signInRequestModel) }
}