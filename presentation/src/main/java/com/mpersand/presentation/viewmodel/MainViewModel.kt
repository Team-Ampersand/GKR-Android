package com.mpersand.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.usecase.equipment.GetAllEquipmentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllEquipmentsUseCase: GetAllEquipmentsUseCase
): ViewModel() {
    fun getAllEquipments() {
        viewModelScope.launch {
            getAllEquipmentsUseCase()
                .onSuccess {
                    Log.d("Success", "getAllEquipments: $it")
                }
                .onFailure {
                    Log.d("Failure", "getAllEquipments: ${it.message}")
                }
        }
    }
}