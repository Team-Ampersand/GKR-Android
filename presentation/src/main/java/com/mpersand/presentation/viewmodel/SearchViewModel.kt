package com.mpersand.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.domain.usecase.equipment.EquipmentFilterUseCase
import com.mpersand.presentation.util.UiState
import com.mpersand.presentation.util.exceptionHandling
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val equipmentFilterUseCase: EquipmentFilterUseCase
): ViewModel() {
    private val _equipmentFilter: MutableLiveData<UiState<List<EquipmentResponseModel>>> = MutableLiveData()
    val equipmentFilter: LiveData<UiState<List<EquipmentResponseModel>>> = _equipmentFilter

    fun searchEquipment(name: String) {
        viewModelScope.launch {
            equipmentFilterUseCase(name).onSuccess {
                _equipmentFilter.value = UiState.Success(it)
            }.onFailure {
                it.exceptionHandling(
                    badRequestAction = { _equipmentFilter.value = UiState.BadRequest },
                    unauthorizedAction = { _equipmentFilter.value = UiState.Unauthorized },
                    forbiddenAction = { _equipmentFilter.value = UiState.Forbidden },
                    notFoundAction = { _equipmentFilter.value = UiState.NotFound }
                )
            }
        }
    }
}