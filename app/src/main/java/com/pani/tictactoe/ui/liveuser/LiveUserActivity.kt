package com.pani.tictactoe.ui.liveuser

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pani.tictactoe.R
import com.pani.tictactoe.StandardActivity
import com.pani.tictactoe.application.utils.RetryListener
import com.pani.tictactoe.model.User
import com.pani.tictactoe.rx.RetrofitRxWrapper
import com.pani.tictactoe.ui.gameboard.GameBoardActivity
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_live_list.*
import org.json.JSONException
import org.json.JSONObject
import java.net.URISyntaxException


class LiveUserActivity : StandardActivity(), LiveUserDelegate, RetryListener,
    LiveUserViewHolder.CustomerClickListner {

    private var menuItemsAdapter: LiveUserListAdapter? = null
    private lateinit var presenter: LiveUserPresenter
    private lateinit var layoutManager: LinearLayoutManager
    val TAG: String = "LiveUserActivity"
    private var viewModel: LiveUserViewModel? = null
    override fun retry() {
        this.getViewModel(LiveUserViewModel::class.java).retry()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_list)

        this.setSupportActionBar(toolbar)
        this.presenter = LiveUserPresenter(this, RetrofitRxWrapper(), isConnectedToInternet())

//        try {
//            socket = IO.socket("http://192.168.31.6:3000")
//
//        } catch (e: URISyntaxException) {
//            e.printStackTrace()
//        }
        this.menuItemsAdapter = LiveUserListAdapter(this)
        this.viewModel = this.getViewModel(LiveUserViewModel::class.java)
//        socket!!.connect()

    }




    private fun setListAdapter() {


        viewModel!!.initialLoading.observe(this, Observer {
            this.presenter.handleInitialLoadingNetworkState(it)
        })

        viewModel!!.networkState.observe(this, Observer {
            this.menuItemsAdapter?.setNetworkState(it)
        })

        viewModel!!.menuItemsLiveData.observe(this, Observer {
            this.menuItemsAdapter?.submitList(it)
        })

        this.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        recyclerView_transactions.layoutManager = this.layoutManager
        recyclerView_transactions.adapter = this.menuItemsAdapter

    }


    override fun onResume() {
        super.onResume()
        this.presenter.getItems()
        setListAdapter()
        socket!!.on("newGame", Emitter.Listener {
            this.presenter.getItems()
        })
    }

    override fun showProgressBar() {
        recyclerView_transactions.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        recyclerView_transactions.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    override fun connecToSocket() {

    }


    companion object {
        fun getLauncherIntent(context: Context): Intent {
            return Intent(context, LiveUserActivity::class.java)
        }

        fun launch(context: Context) {
            context.startActivity(
                getLauncherIntent(
                    context
                )
            )
        }
    }

    override fun onClickEngineer(user: User) {
        user?.let { showMessage(user.name + "" + user.room) }
        GameBoardActivity.launch(this,user)
    }

    override fun onPause() {
        super.onPause()

    }

    override fun onStop() {
        super.onStop()
        val obj = JSONObject()
        obj.put("name", sharedPrefs!!.userName)
        socket!!.emit("stop", obj)

    }

    override fun onStart() {
        super.onStart()
        val obj = JSONObject()
        obj.put("name", sharedPrefs!!.userName)
        socket!!.emit("createGame", obj)

    }
}
