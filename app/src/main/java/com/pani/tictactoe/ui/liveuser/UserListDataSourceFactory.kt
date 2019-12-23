package com.pani.tictactoe.ui.liveuser

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.pani.tictactoe.model.User
import com.pani.tictactoe.rx.RetrofitRxWrapper

class UserListDataSourceFactory(
    private val wrapper: RetrofitRxWrapper,
    private val isNetworkAvailable: Boolean
) : DataSource.Factory<Int, User>() {
    val brandItemsDataSourceLiveData: MutableLiveData<UserListDataSource> = MutableLiveData()
    private lateinit var brandItemsDataSource: UserListDataSource

    override fun create(): DataSource<Int, User> {
        this.brandItemsDataSource = UserListDataSource(
            this.wrapper,isNetworkAvailable)
        this.brandItemsDataSourceLiveData.postValue(this.brandItemsDataSource)
        return this.brandItemsDataSource
    }
}
