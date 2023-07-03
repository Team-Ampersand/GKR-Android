package com.mpersand.data.remote.datasource.user

import com.mpersand.data.dto.user.response.UserResponse
import com.mpersand.data.network.api.UserApi
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userApi: UserApi
): UserDataSource {
    override suspend fun getUser(): UserResponse = userApi.getUser()

    override suspend fun logout() = userApi.logout()
}