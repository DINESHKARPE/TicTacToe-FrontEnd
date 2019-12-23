package com.pani.tictactoe.prefs

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) : SharedPrefsService {
    private val sharedPreferences: SharedPreferences

    init {
        this.sharedPreferences = context.getSharedPreferences(TICTACTOE_PREFS, Context.MODE_PRIVATE)
    }

    override var isJoin: Boolean
        get() = sharedPreferences.getBoolean(IS_JOIN, false)
        set(b) = this.sharedPreferences.edit()
            .putBoolean(IS_JOIN, b)
            .apply()

    override var userName: String
        get() = sharedPreferences.getString(USER_NAME, "").toString()
        set(b) = this.sharedPreferences.edit()
            .putString(USER_NAME, b)
            .apply()





    companion object {
        private val TICTACTOE_PREFS = "TICTACTOE_PREFS"
        private val IS_JOIN = "IS_JOIN"
        private val USER_NAME = "USER_NAME"
    }
}
