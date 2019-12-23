package com.pani.tictactoe.rx

import com.google.gson.annotations.SerializedName
import com.pani.tictactoe.model.User
import java.io.Serializable

class LiveUserListResponse : Serializable{
    @SerializedName("status")
    var status: String? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("code")
    var code: String? = null

    @SerializedName("users")
    val users : List<User> ? = null

}