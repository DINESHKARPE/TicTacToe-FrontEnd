package com.pani.tictactoe.restclient


import com.pani.tictactoe.model.User
import com.pani.tictactoe.rx.UserJoinResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import com.pani.tictactoe.application.utils.Constants
import com.pani.tictactoe.rx.LiveUserListResponse

internal interface RetrofitService {
//    @GET(Constants.Admin.LOGIN)
//    fun adminLogin(
//        @Query("email") email: String,
//        @Query("password") password: String
//    ): Single<AdminLoginResponse>

    @POST(Constants.Admin.ADD_USER)
    fun addUser(@Body name: User): Single<UserJoinResponse>


    @GET(Constants.Admin.FETCH_USER)
    fun fetchLiveUser(): Call<LiveUserListResponse>



}
