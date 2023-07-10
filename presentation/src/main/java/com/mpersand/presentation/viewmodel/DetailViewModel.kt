package com.mpersand.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.domain.model.order.request.OrderRequestModel
import com.mpersand.domain.usecase.equipment.GetEquipmentInfoUseCase
import com.mpersand.domain.usecase.order.PostRentalRequestUseCase
import com.mpersand.presentation.util.UiState
import com.mpersand.presentation.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getEquipmentInfoUseCase: GetEquipmentInfoUseCase,
    private val postRentalRequestUseCase: PostRentalRequestUseCase
): ViewModel() {
    private val _getEquipmentInfoUiState = MutableLiveData<UiState<EquipmentResponseModel>>()
    val getEquipmentInfoUiState: LiveData<UiState<EquipmentResponseModel>> = _getEquipmentInfoUiState

    private val _postRentalRequestUiState = MutableLiveData<UiState<Nothing>>(UiState.Loading)
    val postRentalRequestUiState: LiveData<UiState<Nothing>> = _postRentalRequestUiState

    fun getEquipmentInfo(productNumber: String) {
        viewModelScope.launch {
            getEquipmentInfoUseCase(productNumber)
                .onSuccess {
                    _getEquipmentInfoUiState.value = UiState.Success(it)
                }
                .onFailure {
                    it.exceptionHandling(
                        badRequestAction = {
                            _getEquipmentInfoUiState.value = UiState.BadRequest
                        },
                        unauthorizedAction = {
                            _getEquipmentInfoUiState.value = UiState.Unauthorized
                        },
                        notFoundAction = {
                            _getEquipmentInfoUiState.value = UiState.NotFound
                        }
                    )
                }
        }
    }

    fun postRentalRequest(orderRequest: OrderRequestModel) {
        viewModelScope.launch {
            postRentalRequestUseCase(orderRequest)
                .onSuccess {
                    _postRentalRequestUiState.value = UiState.Success()
                }
                .onFailure {
                    it.exceptionHandling(
                        badRequestAction = {
                            _postRentalRequestUiState.value = UiState.BadRequest
                        },
                        unauthorizedAction = {
                            _postRentalRequestUiState.value = UiState.Unauthorized
                        },
                        notFoundAction = {
                            _postRentalRequestUiState.value = UiState.NotFound
                        }
                    )
                }
        }
    }
}