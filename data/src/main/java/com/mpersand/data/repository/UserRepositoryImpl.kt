package com.mpersand.data.repository

import com.mpersand.data.dto.user.response.asUserResponseModel
import com.mpersand.data.remote.datasource.user.UserDataSource
import com.mpersand.domain.model.response.UserResponseModel
import com.mpersand.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
): UserRepository {
    override suspend fun getUser(): UserResponseModel = userDataSource.getUser().asUserResponseModel()

    override suspend fun logout() = userDataSource.logout()
}