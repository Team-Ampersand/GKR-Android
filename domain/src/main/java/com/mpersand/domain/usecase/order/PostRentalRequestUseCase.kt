package com.mpersand.domain.usecase.order

import com.mpersand.domain.model.order.request.OrderRequestModel
import com.mpersand.domain.repository.OrderRepository
import javax.inject.Inject

class PostRentalRequestUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    suspend operator fun invoke(orderRequest: OrderRequestModel) = kotlin.runCatching { orderRepository.postRentalRequest(orderRequest) }
}