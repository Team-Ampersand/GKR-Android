package com.mpersand.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.violation.ViolationResponseModel
import com.mpersand.domain.usecase.violation.GetViolationHistoryUseCase
import com.mpersand.presentation.util.UiState
import com.mpersand.presentation.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViolationViewModel @Inject constructor(
    private val getViolationHistoryUseCase: GetViolationHistoryUseCase
): ViewModel() {
    private val _getViolationHistory: MutableLiveData<UiState<List<ViolationResponseModel>>> = MutableLiveData()
    val getViolationHistory: LiveData<UiState<List<ViolationResponseModel>>> = _getViolationHistory

    fun getViolationHistory() {
        viewModelScope.launch {
            getViolationHistoryUseCase().onSuccess {
                _getViolationHistory.value = UiState.Success(it)
            }.onFailure {
                it.exceptionHandling(
                    badRequestAction = { _getViolationHistory.value = UiState.BadRequest },
                    unauthorizedAction = { _getViolationHistory.value = UiState.Unauthorized },
                    notFoundAction = { _getViolationHistory.value = UiState.NotFound }
                )
            }
        }
    }
}