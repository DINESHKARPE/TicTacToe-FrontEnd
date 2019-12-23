package com.pani.tictactoe.application.utils

import com.pani.tictactoe.prefs.SharedPrefsService
import javax.inject.Inject

class Constants {
    @set:Inject
    var sharedPrefsService: SharedPrefsService? = null

    @Inject
    constructor() {
    }

    constructor(deliverrSharedPrefs: SharedPrefsService?) {
        this.sharedPrefsService = deliverrSharedPrefs
    }

    object Admin {
        const val ADD_USER = "user"
        const val FETCH_USER = "user"
    }
}