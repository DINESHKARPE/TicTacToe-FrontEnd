package com.pani.tictactoe.restclient

data class ShowCommonDialogEvent(
        val dialogMessage: String,
        val dialogTitle: String,
        val buttonAction: String
)
