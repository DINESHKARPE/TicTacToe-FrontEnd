package com.pani.tictactoe.di.modules


import com.pani.tictactoe.di.scopes.ActivityScope
import com.pani.tictactoe.ui.gameboard.GameBoardActivity
import com.pani.tictactoe.ui.liveuser.LiveUserActivity
import com.pani.tictactoe.ui.newuser.AddUserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBindingModule {

//    @ActivityScope
//    @ContributesAndroidInjector(modules = [])
//    internal abstract fun bindHomeActivity(): MainActivityModule
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = [])
//    internal abstract fun bindMainActivity(): MainActivity
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = [])
//    internal abstract fun bindLoginActivity(): LoginActivity

//    @ActivityScope
//    @ContributesAndroidInjector(modules = [])
//    internal abstract fun bindSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    internal abstract fun bindAddUserActivity(): AddUserActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    internal  abstract fun bindLiveUserActivity(): LiveUserActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    internal abstract fun bindGameBoardActivity() : GameBoardActivity

//    @ActivityScope
//    @ContributesAndroidInjector(modules = [CustomerListActivityModule::class])
//    internal abstract fun bindSBrandItemsActivity(): CustomersListActivity
//
//    @ActivityScope
//    @ContributesAndroidInjector(modules = [])
//    internal abstract fun bindLogoutActivity(): LogoutActivit

}
