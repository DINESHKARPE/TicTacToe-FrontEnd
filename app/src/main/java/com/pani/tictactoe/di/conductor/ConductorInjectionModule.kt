package com.pani.tictactoe.di.conductor

import com.bluelinelabs.conductor.Controller
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.Multibinds

/**
 * Conductor injection module
 *
 *
 * Note: the use of java here due to Kotlin's weird handling of generics
 */
@Module
abstract class ConductorInjectionModule {
    @Multibinds
    abstract fun controllerInjectorFactories(): Map<Class<out Controller?>?, AndroidInjector.Factory<*>?>?

    @Multibinds
    abstract fun controllerStringInjectorFactories(): Map<String?, AndroidInjector.Factory<out Controller?>?>?
}