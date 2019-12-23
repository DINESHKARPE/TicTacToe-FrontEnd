package com.pani.tictactoe.ui.gameboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import com.pani.tictactoe.R
import com.pani.tictactoe.StandardActivity
import com.pani.tictactoe.model.User
import com.pani.tictactoe.rx.RetrofitRxWrapper
import kotlinx.android.synthetic.main.activity_gameboard.*
import kotlinx.android.synthetic.main.activity_live_list.*
import org.json.JSONObject


class GameBoardActivity : StandardActivity(), GameBoardDelegate {

    private val boardCells = Array(3) { arrayOfNulls<Button>(3) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameboard)
        this.setSupportActionBar(toolbar)

        if (getIntent().hasExtra(KEY_USER) && getIntent().hasExtra(KEY_ROOM)) {
            var user = getIntent().extras!!.getString(KEY_USER) as String
            var room = getIntent().extras!!.getString(KEY_ROOM) as String
            supportActionBar?.setTitle("Game Board")
            val obj = JSONObject()
            obj.put("name",user)
            obj.put("room",user)
            socket!!.emit("joinGame", obj)
        }

        bindViews()


    }


    private fun bindViews() {
        b00.setOnClickListener { clickAddButton() }

    }

    fun clickAddButton() {

    }

    fun clickBackButton() {
        finish()
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                clickBackButton()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun hideKeyboard() {
        deliverr.deliverrconsumer.utils.hideKeyboard(this)
    }


    companion object {
        private const val KEY_USER = "KEY_USER"
        private const val KEY_ROOM = "KEY_ROOM"
        fun getLauncherIntent(context: Context): Intent {
            return Intent(context, GameBoardActivity::class.java)
        }


        fun launch(context: Context, user: User) {
            context.startActivity(getLauncherIntent(context, user))
        }

        fun launch(context: Context) {
            context.startActivity(getLauncherIntent(context))
        }

        fun getLauncherIntent(
            context: Context,
            user: User
        ): Intent {
            return Intent(context, GameBoardActivity::class.java).apply {
                putExtra(KEY_USER, user.name)
                putExtra(KEY_ROOM, user.room)
            }
        }
    }
}
