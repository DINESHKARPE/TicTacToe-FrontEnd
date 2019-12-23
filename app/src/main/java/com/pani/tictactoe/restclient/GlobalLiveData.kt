package com.pani.tictactoe.restclient

import androidx.lifecycle.MutableLiveData

object GlobalLiveData {
    var logOutLiveData: MutableLiveData<SingleEvent<String>> = MutableLiveData()
    var versionRestrictLiveData: MutableLiveData<SingleEvent<ShowCommonDialogEvent>> = MutableLiveData()
}
