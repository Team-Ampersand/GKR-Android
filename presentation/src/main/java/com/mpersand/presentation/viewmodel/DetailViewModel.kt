package com.mpersand.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpersand.domain.model.order.request.OrderRequestModel
import com.mpersand.domain.usecase.equipment.GetEquipmentInfoUseCase
import com.mpersand.domain.usecase.order.PostRentalRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getEquipmentInfoUseCase: GetEquipmentInfoUseCase,
    private val postRentalRequestUseCase: PostRentalRequestUseCase
): ViewModel() {
    fun getEquipmentInfo(productNumber: String) {
        viewModelScope.launch {
            getEquipmentInfoUseCase(productNumber)
                .onSuccess {
                    Log.d("Success" , "getEquipmentInfo: $it")
                }
                .onFailure {
                    Log.d("Failure " , "getEquipmentInfo: ${it.message}")
                }
        }
    }

    fun postRentalRequest(orderRequest: OrderRequestModel) {
        viewModelScope.launch {
            postRentalRequestUseCase(orderRequest)
                .onSuccess {
                    Log.d("Success", "postRentalRequest: $it")
                }
                .onFailure {
                    Log.d("Failure", "postRentalRequest: ${it.message}")
                }
        }
    }
}