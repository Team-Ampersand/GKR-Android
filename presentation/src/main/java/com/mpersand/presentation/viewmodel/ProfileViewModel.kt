package com.mpersand.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.domain.model.response.UserResponseModel
import com.mpersand.domain.usecase.order.EquipmentRentalByUserUseCase
import com.mpersand.domain.usecase.user.GetUserUseCase
import com.mpersand.domain.usecase.user.LogoutUseCase
import com.mpersand.presentation.util.UiState
import com.mpersand.presentation.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val equipmentRentalByUserUseCase: EquipmentRentalByUserUseCase
): ViewModel() {
    private val _getUser: MutableLiveData<UiState<UserResponseModel?>> = MutableLiveData()
    val getUser: LiveData<UiState<UserResponseModel?>> = _getUser

    private val _logout: MutableLiveData<UiState<Unit>> = MutableLiveData()
    val logout: LiveData<UiState<Unit>> = _logout

    private val _getListByUser: MutableLiveData<UiState<List<EquipmentResponseModel>>> = MutableLiveData()
    val getListByUser: LiveData<UiState<List<EquipmentResponseModel>>> = _getListByUser

    fun getUser() {
        viewModelScope.launch {
            getUserUseCase().onSuccess {
                _getUser.value = UiState.Success(it)
            }.onFailure {
                it.exceptionHandling(
                    badRequestAction = {
                        _getUser.value = UiState.BadRequest
                    },
                    unauthorizedAction = {
                        _getUser.value = UiState.Unauthorized
                    },
                    notFoundAction = {
                        _getUser.value = UiState.NotFound
                    }
                )
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase().onSuccess {
                _logout.value = UiState.Success(it)
            }.onFailure {
                it.exceptionHandling(
                    badRequestAction = {
                        _logout.value = UiState.BadRequest
                    },
                    unauthorizedAction = {
                        _logout.value = UiState.Unauthorized
                    },
                    notFoundAction = {
                        _logout.value = UiState.NotFound
                    }
                )
            }
        }
    }

    fun getEquipmentRentalListByUser() {
        viewModelScope.launch {
            equipmentRentalByUserUseCase().onSuccess {
                _getListByUser.value = UiState.Success(it)
            }.onFailure {
                it.exceptionHandling(
                    badRequestAction = {
                        UiState.BadRequest
                    },
                    unauthorizedAction = {
                        UiState.Unauthorized
                    },
                    notFoundAction = {
                        UiState.NotFound
                    }
                )
            }
        }
    }
}