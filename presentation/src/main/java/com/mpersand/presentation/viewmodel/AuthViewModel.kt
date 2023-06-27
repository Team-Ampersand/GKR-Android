package com.mpersand.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.request.SignInRequestModel
import com.mpersand.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {
    fun signIn(code: String) {
        viewModelScope.launch {
            signInUseCase(SignInRequestModel(code = code))
                .onSuccess {
                    Log.d("signIn", it.toString())
                }.onFailure {
                    Log.d("signIn", it.message.toString())
                }

        }
    }
}