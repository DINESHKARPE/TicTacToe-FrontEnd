package com.pani.tictactoe.di.views

import android.view.View
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.Multibinds

@Module
abstract class ViewInjectionModule {
    @Multibinds
    abstract fun viewInjectorFactories(): Map<Class<out View?>?, AndroidInjector.Factory<out View?>?>?

    @Multibinds
    abstract fun viewStringInjectorFactories(): Map<String?, AndroidInjector.Factory<out View?>?>?
}