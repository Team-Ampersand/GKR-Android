package com.mpersand.di.modules

import com.mpersand.data.remote.datasource.auth.AuthDataSource
import com.mpersand.data.remote.datasource.auth.AuthDataSourceImpl
import com.mpersand.data.remote.datasource.equipment.EquipmentDataSource
import com.mpersand.data.remote.datasource.equipment.EquipmentDataSourceImpl
import com.mpersand.data.remote.datasource.order.OrderDataSource
import com.mpersand.data.remote.datasource.order.OrderDataSourceImpl
import com.mpersand.data.remote.datasource.user.UserDataSource
import com.mpersand.data.remote.datasource.user.UserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {
    @Binds
    fun bindsAuthDataSource(
        authDataSourceImpl: AuthDataSourceImpl
    ): AuthDataSource

    @Binds
    fun bindsEquipmentDataSource(
        equipmentDataSourceImpl: EquipmentDataSourceImpl
    ): EquipmentDataSource

    @Binds
    fun bindsOrderDataSource(
        orderDataSourceImpl: OrderDataSourceImpl
    ): OrderDataSource

    @Binds
    fun bindsUserDataSource(
        userDataSourceImpl: UserDataSourceImpl
    ): UserDataSource
}