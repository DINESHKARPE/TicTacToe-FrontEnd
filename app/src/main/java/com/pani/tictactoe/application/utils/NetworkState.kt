package com.pani.tictactoe.application.utils

val LOADING = NetworkState(NetworkStatus.RUNNING, "Running")
val LOADED = NetworkState(NetworkStatus.SUCCESS, "Success")

data class NetworkState(
        val status: NetworkStatus,
        val message: String
)

enum class NetworkStatus {
    RUNNING,
    SUCCESS,
    FAILED
}
