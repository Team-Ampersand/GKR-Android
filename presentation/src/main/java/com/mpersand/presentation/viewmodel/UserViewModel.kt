package com.mpersand.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.response.UserResponseModel
import com.mpersand.domain.usecase.user.GetUserUseCase
import com.mpersand.domain.usecase.user.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase
): ViewModel() {
    private val _getUser: MutableLiveData<UserResponseModel?> = MutableLiveData()
    val getUser: LiveData<UserResponseModel?> = _getUser

    private val _logout: MutableLiveData<Boolean> = MutableLiveData()
    val logout: LiveData<Boolean> = _logout

    fun getUser() {
        viewModelScope.launch {
            getUserUseCase().onSuccess {
                _getUser.value = it
            }.onFailure {
                _getUser.value = null
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase().onSuccess {
                _logout.value = true
            }.onFailure {
                _logout.value = false
            }
        }
    }
}