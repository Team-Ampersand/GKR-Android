package com.mpersand.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.auth.request.SignInRequestModel
import com.mpersand.domain.usecase.auth.IsLoginUseCase
import com.mpersand.domain.usecase.auth.SaveTokenUseCase
import com.mpersand.domain.usecase.auth.SignInUseCase
import com.mpersand.presentation.util.UiState
import com.mpersand.presentation.util.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val isLoginUseCase: IsLoginUseCase
) : ViewModel() {
    private val _loginUiState = MutableLiveData<UiState<Boolean>>(UiState.Loading)
    val loginUiState: LiveData<UiState<Boolean>> = _loginUiState

    private val _signInUiState = MutableLiveData<UiState<Nothing>>()
    val signInUiState: LiveData<UiState<Nothing>> = _signInUiState

    fun isLogin() {
        viewModelScope.launch {
            isLoginUseCase()
                .onSuccess {
                    _loginUiState.value = UiState.Success(it)
                }.onFailure {
                    it.exceptionHandling(
                        unknownAction = { _loginUiState.value = UiState.Unknown }
                    )
                }
        }
    }

    fun signIn(code: String) {
        viewModelScope.launch {
            signInUseCase(SignInRequestModel(code = code))
                .onSuccess {
                    saveTokenUseCase(
                        accessToken = it.accessToken,
                        refreshToken = it.refreshToken,
                        accessTokenExp = it.accessTokenExp,
                        refreshTokenExp = it.refreshTokenExp
                    )
                    _signInUiState.value = UiState.Success()
                }.onFailure {
                    it.exceptionHandling(
                        badRequestAction = {
                            _signInUiState.value = UiState.BadRequest
                        }
                    )
                }
        }
    }
}