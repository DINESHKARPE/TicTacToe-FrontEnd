package com.pani.tictactoe.di.views

import android.view.View
import dagger.android.AndroidInjector

interface HasViewInjector {
    fun viewInjector(): AndroidInjector<View>
}