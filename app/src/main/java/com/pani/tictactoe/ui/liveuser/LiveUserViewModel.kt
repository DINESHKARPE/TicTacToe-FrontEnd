package com.pani.tictactoe.ui.liveuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.pani.tictactoe.application.utils.NetworkState
import com.pani.tictactoe.model.User
import com.pani.tictactoe.rx.RetrofitRxWrapper

class LiveUserViewModel : ViewModel() {




    lateinit var initialLoading: LiveData<NetworkState>
    lateinit var networkState: LiveData<NetworkState>
    lateinit var menuItemsLiveData: LiveData<PagedList<User>>

    lateinit var brandItemsDataSource: LiveData<UserListDataSource>

    fun retry() {
        this.brandItemsDataSource.value!!.retryLoading()
    }

    fun initItems(wrapper: RetrofitRxWrapper,
                  isNetworkAvailable: Boolean) {
        val brandItemsDataSourceFactory = UserListDataSourceFactory(
            wrapper,isNetworkAvailable
        )
        this.brandItemsDataSource = brandItemsDataSourceFactory.brandItemsDataSourceLiveData

        this.initialLoading = Transformations.switchMap(
            brandItemsDataSourceFactory.brandItemsDataSourceLiveData
        ) { it.initialLoading }
        this.networkState = Transformations.switchMap(
            brandItemsDataSourceFactory.brandItemsDataSourceLiveData
        ) { it.networkState }

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(INITIAL_PAGE_SIZE)
            .setPageSize(FUTURE_PAGE_SIZE)
            .setPrefetchDistance(FUTURE_PAGE_SIZE)
            .build()

        this.menuItemsLiveData = LivePagedListBuilder(brandItemsDataSourceFactory, pagedListConfig)
            .build()
    }



}
