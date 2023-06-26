package com.mpersand.data.dto.response

data class ReissueTokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: String,
    val refreshTokenExp: String
)
