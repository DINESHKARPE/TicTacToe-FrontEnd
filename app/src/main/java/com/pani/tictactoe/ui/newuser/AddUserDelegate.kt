package com.pani.tictactoe.ui.newuser

import androidx.lifecycle.ViewModel

internal interface AddUserDelegate {

    fun isConnectedToInternet(): Boolean
    fun getName(): String
    fun showMessage(stringMessage: String)
    fun showMessage(stringMessageid: Int)
    fun finishActivity()
    fun hideKeyboard()
    fun <T : ViewModel> getViewModel(clazz: Class<T>): T
    fun hideProgressBar()
    fun showProgressBar()
    fun removeUserNameError()
    fun clearData()
    fun redirectUser()
    fun showInputError(message: String?)
    fun showLiveUser(name:String)

}
