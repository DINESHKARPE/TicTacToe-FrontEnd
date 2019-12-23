package com.pani.tictactoe.ui.liveuser


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.pani.tictactoe.application.utils.LOADED
import com.pani.tictactoe.application.utils.LOADING
import com.pani.tictactoe.application.utils.NetworkState
import com.pani.tictactoe.application.utils.NetworkStatus
import com.pani.tictactoe.model.User
import com.pani.tictactoe.rx.LiveUserListResponse
import com.pani.tictactoe.rx.RetrofitRxWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


const val INITIAL_PAGE_SIZE = 10
const val FUTURE_PAGE_SIZE = 5

class UserListDataSource(
    private val wrapper: RetrofitRxWrapper,
    private val isNetworkAvailable: Boolean
) : PageKeyedDataSource<Int, User>() {
    val initialLoading: MutableLiveData<NetworkState> = MutableLiveData()
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    private var retryInitialLoad = true
    private lateinit var loadInitialParams: LoadInitialParams<Int>
    private lateinit var loadInitialCallback: LoadInitialCallback<Int, User>
    private lateinit var loadParams: LoadParams<Int>
    private lateinit var loadCallback: LoadCallback<Int, User>

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {
        this.initialLoading.postValue(LOADING)
        this.networkState.postValue(LOADING)

        this.retryInitialLoad = true
        this.loadInitialParams = params
        this.loadInitialCallback = callback


        this.wrapper.fetchLiveUser().enqueue(object : Callback<LiveUserListResponse> {
            override fun onFailure(call: Call<LiveUserListResponse>, t: Throwable?) {
                val errorMessage = t?.message ?: "Unknown error"

                Log.e("UserListDataSource",t.toString())
                initialLoading.postValue(NetworkState(NetworkStatus.FAILED, errorMessage))
                networkState.postValue(NetworkState(NetworkStatus.FAILED, errorMessage))
            }

            override fun onResponse(
                call: Call<LiveUserListResponse>,
                response: Response<LiveUserListResponse>?
            ) {
                if ((response != null) && response.isSuccessful) {
                    if (response.body()!!.code == "U200") {
                        response.body()!!.users?.let {
                            callback.onResult(it, null, INITIAL_PAGE_SIZE
                            )
                        }?: run {
                            initialLoading.postValue(NetworkState(NetworkStatus.FAILED, "User's Not Found"))
                        }
                        initialLoading.postValue(LOADED)
                        networkState.postValue(LOADED)
                    }
                } else {
                    initialLoading.postValue(
                        NetworkState(
                            NetworkStatus.FAILED,
                            response!!.message()
                        )
                    )
                    networkState.postValue(
                        NetworkState(
                            NetworkStatus.FAILED,
                            response.message()
                        )
                    )
                }
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        networkState.postValue(LOADING)

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        // This is not implemented because we only load next items
    }

    fun retryLoading() {
        if (this.retryInitialLoad) {
            this.loadInitial(this.loadInitialParams, this.loadInitialCallback)
        } else {
            this.loadAfter(this.loadParams, this.loadCallback)
        }
    }
}

