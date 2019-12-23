package com.pani.tictactoe.di.modules

import android.app.Application
import com.pani.tictactoe.prefs.SharedPrefs
import com.pani.tictactoe.prefs.SharedPrefsService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPrefsModule {
    @Provides
    @Singleton
    fun provideDeliverrSharedPrefsService(application: Application): SharedPrefsService {
        return SharedPrefs(application)
    }
}