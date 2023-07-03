package com.mpersand.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.domain.usecase.equipment.GetAllEquipmentsUseCase
import com.mpersand.presentation.util.UiState
import com.mpersand.presentation.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllEquipmentsUseCase: GetAllEquipmentsUseCase
): ViewModel() {
    private val _getAllEquipmentsUiState = MutableLiveData<UiState<List<EquipmentResponseModel>>>()
    val getAllEquipmentsUiState: LiveData<UiState<List<EquipmentResponseModel>>> = _getAllEquipmentsUiState
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
}