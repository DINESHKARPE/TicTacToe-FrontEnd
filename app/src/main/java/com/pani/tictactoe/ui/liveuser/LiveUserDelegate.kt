package com.pani.tictactoe.ui.liveuser

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel

internal interface LiveUserDelegate {

    fun showProgressBar()
    fun hideProgressBar()

    fun showMessage(message: String)
    fun showMessage(@StringRes messageId: Int)

    fun isConnectedToInternet(): Boolean
    fun <T : ViewModel> getViewModel(clazz: Class<T>): T
    fun connecToSocket()


}
