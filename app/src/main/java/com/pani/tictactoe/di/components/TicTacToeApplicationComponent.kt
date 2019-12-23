package com.pani.tictactoe.di.components

import android.app.Application
import com.pani.tictactoe.application.TicTacToeApp
import com.pani.tictactoe.di.conductor.ConductorInjectionModule
import com.pani.tictactoe.di.modules.ActivityBindingModule
import com.pani.tictactoe.di.modules.ConductorBindingModule
import com.pani.tictactoe.di.modules.SharedPrefsModule
import com.pani.tictactoe.di.modules.ViewBindingModule
import com.pani.tictactoe.di.views.ViewInjectionModule
import com.pini.tictacttoe.di.modules.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        SharedPrefsModule::class,
        AndroidSupportInjectionModule::class,
        ConductorInjectionModule::class,
        ViewInjectionModule::class,
        ActivityBindingModule::class,
        ConductorBindingModule::class,
        ViewBindingModule::class
    ]
)
interface TicTacToeApplicationComponent : AndroidInjector<TicTacToeApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TicTacToeApplicationComponent
    }


}
