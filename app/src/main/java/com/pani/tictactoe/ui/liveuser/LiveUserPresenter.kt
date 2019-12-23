package com.pani.tictactoe.ui.liveuser

import android.util.Log
import com.pani.tictactoe.R
import com.pani.tictactoe.application.utils.NetworkState
import com.pani.tictactoe.application.utils.NetworkStatus
import com.pani.tictactoe.rx.RetrofitRxWrapper
import io.reactivex.disposables.Disposable
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject
import java.net.URISyntaxException

internal class LiveUserPresenter(private val viewDelegate: LiveUserDelegate, private val wrapper: RetrofitRxWrapper, private val isNetworkAvailable: Boolean) {

    val viewModel: LiveUserViewModel by lazy {
        this.viewDelegate.getViewModel(LiveUserViewModel::class.java)
    }
    private var disposable: Disposable? = null

    fun handleInitialLoadingNetworkState(networkState: NetworkState) {

        when (networkState.status) {
            NetworkStatus.RUNNING -> this.viewDelegate.showProgressBar()
            NetworkStatus.SUCCESS -> {
                this.viewDelegate.connecToSocket()

                this.viewDelegate.hideProgressBar()
            }
            NetworkStatus.FAILED -> {
                this.viewDelegate.hideProgressBar()
                if (networkState.message.isNotBlank()) {
                    this.viewDelegate.showMessage(networkState.message)
                } else {
                    this.viewDelegate.showMessage(R.string.error_name)
                }
            }
        }
    }

    fun getItems() {
        viewModel.initItems(wrapper, isNetworkAvailable)
    }


    fun success() {
        getItems()
    }

    fun failed(msg: String) {
        viewDelegate.showMessage(msg)
    }


//    fun getDetail() {
//        if (viewDelegate.isConnectedToInternet()) {
//            viewDelegate.showProgressBar()
//            getAnalyticsDetails()
//        }
//    }

}
