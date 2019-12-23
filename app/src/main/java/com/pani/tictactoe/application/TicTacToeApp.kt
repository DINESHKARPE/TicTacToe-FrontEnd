package com.pani.tictactoe.application

import android.content.Context
import android.view.View
import androidx.multidex.MultiDex
import com.bluelinelabs.conductor.Controller
import com.pani.tictactoe.di.components.DaggerTicTacToeApplicationComponent
import com.pani.tictactoe.di.conductor.HasConductorInjector
import com.pani.tictactoe.di.views.HasViewInjector
import com.pani.tictactoe.prefs.SharedPrefsService
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class TicTacToeApp : DaggerApplication(), HasConductorInjector,
    HasViewInjector {
    @set:Inject
    var sharedPrefsService: SharedPrefsService? = null
    @set:Inject
    var controllerInjector: DispatchingAndroidInjector<Controller>? = null
    @set:Inject
    var viewInjector: DispatchingAndroidInjector<View>? = null

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerTicTacToeApplicationComponent.builder().application(this).build()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    override fun controllerInjector(): DispatchingAndroidInjector<Controller> {
        return controllerInjector!!
    }

    override fun viewInjector(): AndroidInjector<View> {
        return viewInjector!!
    }

    companion object {
        val TAG =
            TicTacToeApp::class.java.simpleName
    }
}