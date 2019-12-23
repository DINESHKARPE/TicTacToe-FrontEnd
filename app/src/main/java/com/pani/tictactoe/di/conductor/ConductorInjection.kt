package com.pani.tictactoe.di.conductor

import com.bluelinelabs.conductor.Controller
import dagger.android.AndroidInjector
import dagger.internal.Preconditions

object ConductorInjection {
    /**
     * Injects the [Controller]
     */
    fun inject(controller: Controller) {
        Preconditions.checkNotNull(controller, "controller")
        val hasDispatchingControllerInjector =
            findHasControllerInjector(controller)
        val controllerInjector: AndroidInjector<Controller> =
            hasDispatchingControllerInjector.controllerInjector()
        Preconditions.checkNotNull(
            controllerInjector, "%s.controllerInjector() returned null",
            hasDispatchingControllerInjector.javaClass.canonicalName
        )
        controllerInjector.inject(controller)
    }

    private fun findHasControllerInjector(controller: Controller): HasConductorInjector {
        var parentController = controller
        do {
            if (parentController.parentController.also { parentController = it!! } == null) {
                val activity = controller.activity
                if (activity is HasConductorInjector) {
                    return activity
                }
                if (activity!!.application is HasConductorInjector) {
                    return activity.application as HasConductorInjector
                }
                throw IllegalArgumentException(
                    String.format(
                        "No injector was found for %s",
                        controller.javaClass.canonicalName
                    )
                )
            }
        } while (parentController !is HasConductorInjector)
        return parentController as HasConductorInjector
    }
}