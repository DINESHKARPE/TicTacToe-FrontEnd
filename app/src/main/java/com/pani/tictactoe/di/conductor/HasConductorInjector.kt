package com.pani.tictactoe.di.conductor

import com.bluelinelabs.conductor.Controller
import dagger.android.DispatchingAndroidInjector

interface HasConductorInjector {
    fun controllerInjector(): DispatchingAndroidInjector<Controller>
}