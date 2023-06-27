package com.mpersand.data.dto.auth.request

import com.mpersand.domain.model.request.SignInRequestModel

data class SignInRequest(
    val code: String
)

fun SignInRequestModel.asSignInRequest() = SignInRequest(
    code = code
)