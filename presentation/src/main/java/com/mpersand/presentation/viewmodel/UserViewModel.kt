package com.mpersand.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.response.UserResponseModel
import com.mpersand.domain.usecase.user.GetUserUseCase
import com.mpersand.domain.usecase.user.LogoutUseCase
import com.mpersand.presentation.util.UiState
import com.mpersand.presentation.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase
): ViewModel() {
    private val _getUser: MutableLiveData<UiState<UserResponseModel?>> = MutableLiveData()
    val getUser: LiveData<UiState<UserResponseModel?>> = _getUser

    private val _logout: MutableLiveData<UiState<Unit>> = MutableLiveData()
    val logout: LiveData<UiState<Unit>> = _logout

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
}