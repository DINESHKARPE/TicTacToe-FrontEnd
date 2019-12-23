package com.pani.tictactoe.ui.newuser

import android.text.Editable
import android.text.TextWatcher
import com.pani.tictactoe.R
import com.pani.tictactoe.model.User
import com.pani.tictactoe.rx.UserJoinResponse
import com.pani.tictactoe.rx.RetrofitRxWrapper
import com.pani.tictactoe.restclient.BaseServiceRxWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

internal class AddUserPresenter(
    private val delegate: AddUserDelegate,
    private val wrapper: RetrofitRxWrapper
) : TextWatcher {
    val viewModel: AddUserViewModel by lazy {
        this.delegate.getViewModel(AddUserViewModel::class.java)
    }
    private var disposable: Disposable? = null

    fun addUser() {
        addUserWrapper()

        if (delegate.isConnectedToInternet()) {
            delegate.hideKeyboard()
            delegate.showProgressBar()
            addUserWrapper()

        } else {
            delegate.showMessage(R.string.no_internet_connection_message)
        }
    }





    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        val text = s.toString()
        if (text.isNotEmpty()) {
            if (text == delegate.getName()) {
                delegate.removeUserNameError()
            }
        }
    }

    override fun afterTextChanged(s: Editable) {

    }

    fun addUserWrapper() {

        if (viewModel.addResponseSingle == null) {
            val user = User()
            user.name = delegate.getName()
            viewModel.addResponseSingle = wrapper.addUser(user)
        }
        disposable?.dispose()

        disposable = this.viewModel.addResponseSingle?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<UserJoinResponse>() {
                override fun onSuccess(loginResponse: UserJoinResponse) {

                    viewModel.addResponse = loginResponse
                    viewModel.addResponseSingle = null

                    if(loginResponse.code == "U400"){
                        loginResponse.message?.let { userJoinFailed(loginResponse.message) }
                        delegate.showInputError(loginResponse.message)
                    }else{
                        loginResponse.message?.let { userJoinSuccessFully(loginResponse.message) }
                    }
                }

                override fun onError(e: Throwable) {
                    viewModel.addResponseSingle = null
                    var message: String
                    if (e is UnknownHostException) {
                        message = BaseServiceRxWrapper.NO_INTERNET_CONNECTION
                    } else {
                        message = e.message.toString()
                    }
                    userJoinFailed(message)
                    dispose()
                }
            })
    }

    private fun userJoinSuccessFully(message: String) {

        delegate.showMessage(message)
        delegate.showLiveUser(message)
//        delegate.clearData()
        delegate.hideProgressBar()

    }

    private fun userJoinFailed(message: String) {
        delegate.hideProgressBar()
        delegate.showMessage(message)
        delegate.hideProgressBar()
    }


}
