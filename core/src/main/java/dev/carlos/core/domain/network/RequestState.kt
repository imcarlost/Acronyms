package dev.carlos.core.domain.network

sealed class RequestState {
    class Success<out T>(val data: T) : RequestState()
    object Loading : RequestState()
    class Error(val type: RequestError) : RequestState()
    object Empty : RequestState()
}
