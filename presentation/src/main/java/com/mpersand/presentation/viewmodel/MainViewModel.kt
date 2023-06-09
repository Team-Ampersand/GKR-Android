package com.mpersand.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.domain.model.response.UserResponseModel
import com.mpersand.domain.usecase.equipment.EquipmentFilterUseCase
import com.mpersand.domain.usecase.equipment.GetAllEquipmentsUseCase
import com.mpersand.domain.usecase.user.GetUserUseCase
import com.mpersand.presentation.util.UiState
import com.mpersand.presentation.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllEquipmentsUseCase: GetAllEquipmentsUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val equipmentFilterUseCase: EquipmentFilterUseCase
): ViewModel() {
    private val _getAllEquipmentsUiState = MutableLiveData<UiState<List<EquipmentResponseModel>>>()
    val getAllEquipmentsUiState: LiveData<UiState<List<EquipmentResponseModel>>> = _getAllEquipmentsUiState

    private val _mainFilter = MutableLiveData<UiState<List<EquipmentResponseModel>>>()
    val mainFilter: LiveData<UiState<List<EquipmentResponseModel>>> = _mainFilter

    private val _getUserInfoUiState = MutableLiveData<UiState<UserResponseModel>>()
    val getUserInfoUiState: LiveData<UiState<UserResponseModel>> = _getUserInfoUiState

    fun getAllEquipments() {
        viewModelScope.launch {
            getAllEquipmentsUseCase()
                .onSuccess {
                    _getAllEquipmentsUiState.value = UiState.Success(it)
                }
                .onFailure {
                    it.exceptionHandling(
                        badRequestAction = {
                            _getAllEquipmentsUiState.value = UiState.BadRequest
                        },
                        unauthorizedAction = {
                            _getAllEquipmentsUiState.value = UiState.Unauthorized
                        },
                        notFoundAction = {
                            _getAllEquipmentsUiState.value = UiState.NotFound
                        }
                    )
                }
        }
    }

    fun mainFilter(keyword: String) {
        viewModelScope.launch {
            equipmentFilterUseCase(keyword).onSuccess {
                _mainFilter.value = UiState.Success(it)
            }.onFailure {
                it.exceptionHandling(
                    badRequestAction = {
                        _mainFilter.value = UiState.BadRequest
                    },
                    unauthorizedAction = {
                        _mainFilter.value = UiState.Unauthorized
                    },
                    notFoundAction = {
                        _mainFilter.value = UiState.NotFound
                    }
                )
            }
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            getUserUseCase()
                .onSuccess {
                    _getUserInfoUiState.value = UiState.Success(it)
                }
                .onFailure {
                    it.exceptionHandling(
                        badRequestAction = {
                            _getUserInfoUiState.value = UiState.BadRequest
                        },
                        unauthorizedAction = {
                            _getUserInfoUiState.value = UiState.Unauthorized
                        },
                        notFoundAction = {
                            _getUserInfoUiState.value = UiState.NotFound
                        }
                    )
                }
        }
    }
}