package com.pani.tictactoe.ui.gameboard

import androidx.lifecycle.ViewModel

internal interface GameBoardDelegate {

    fun isConnectedToInternet(): Boolean
//    fun getName(): String
//    fun showMessage(stringMessage: String)
//    fun showMessage(stringMessageid: Int)
//    fun finishActivity()
    fun hideKeyboard()
    fun <T : ViewModel> getViewModel(clazz: Class<T>): T
//    fun hideProgressBar()
//    fun showProgressBar()
//    fun removeUserNameError()
//    fun clearData()
//    fun redirectUser()
//    fun showInputError(message: String?)
//    fun showLiveUser(name:String)

}
