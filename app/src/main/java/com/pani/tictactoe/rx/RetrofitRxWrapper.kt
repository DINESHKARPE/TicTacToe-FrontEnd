package com.pani.tictactoe.rx


import com.pani.tictactoe.model.User
import com.pani.tictactoe.restclient.BaseServiceRxWrapper
import com.pani.tictactoe.restclient.RetrofitService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call

class RetrofitRxWrapper : BaseServiceRxWrapper() {
    private val service: RetrofitService = this.retrofit.create(RetrofitService::class.java)

    fun addUser(user: User
    ): Single<UserJoinResponse> {
        return this.service.addUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .cache()
    }
    fun fetchLiveUser(): Call<LiveUserListResponse> {
        return this.service.fetchLiveUser()
    }


}
