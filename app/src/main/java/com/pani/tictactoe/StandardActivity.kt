package com.pani.tictactoe

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.pani.tictactoe.prefs.SharedPrefsService
import dagger.android.support.DaggerAppCompatActivity
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException
import javax.inject.Inject


abstract class StandardActivity : DaggerAppCompatActivity(),

    ConnectivityReceiver.ConnectivityReceiverListener
{
    private var mSnackBar: Snackbar? = null
    var isConnected: Boolean = false

    var socket: Socket? = null

    @set:Inject
    var sharedPrefs: SharedPrefsService? = null





    var receiver: ConnectivityReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiver = ConnectivityReceiver()
        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        try {
            socket = IO.socket("http://192.168.31.6:3000")
            socket!!.connect()


        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }




    fun showMessage(@StringRes messageId: Int) {
        this.showMessage(this.getString(messageId))
    }

    fun showMessage(message: String) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    fun <T : ViewModel> getViewModel(clazz: Class<T>): T {
        return ViewModelProviders.of(this).get(clazz)
    }

    open fun isConnectedToInternet(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork == null) {
            // connected to the internet
            return false
        }
        // not connected to the internet
        return true
    }

    private fun showMessage(isConnected: Boolean) {
        if (!isConnected) {
            Toast.makeText(this, " You are offline", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, " You are Online now", Toast.LENGTH_LONG).show()
        }
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        this.isConnected = isConnected
//        showMessage(isConnected)
    }


}
