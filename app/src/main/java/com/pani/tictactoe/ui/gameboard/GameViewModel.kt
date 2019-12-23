package com.pani.tictactoe.ui.gameboard

import androidx.lifecycle.ViewModel
import com.pani.tictactoe.rx.UserJoinResponse
import io.reactivex.Single

class GameViewModel : ViewModel() {

    var addResponseSingle: Single<UserJoinResponse>? = null
    var addResponse: UserJoinResponse? = null

}
