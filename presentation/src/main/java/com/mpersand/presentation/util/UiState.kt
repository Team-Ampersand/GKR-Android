package com.mpersand.presentation.util

sealed interface UiState<out T> {
    object Loading: UiState<Nothing>
    data class Success<T>(val data: T? = null): UiState<T>
    object BadRequest: UiState<Nothing>
    object Unauthorized: UiState<Nothing>
    object Forbidden: UiState<Nothing>
    object NotFound: UiState<Nothing>
    object TimeOut: UiState<Nothing>
    object Conflict: UiState<Nothing>
    object Server: UiState<Nothing>
    object Unknown: UiState<Nothing>
}