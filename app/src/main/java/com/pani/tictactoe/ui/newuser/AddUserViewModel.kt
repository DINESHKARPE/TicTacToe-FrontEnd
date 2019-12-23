package com.pani.tictactoe.ui.newuser

import androidx.lifecycle.ViewModel
import com.pani.tictactoe.rx.UserJoinResponse
import io.reactivex.Single

class AddUserViewModel : ViewModel() {

    var addResponseSingle: Single<UserJoinResponse>? = null
    var addResponse: UserJoinResponse? = null

}
