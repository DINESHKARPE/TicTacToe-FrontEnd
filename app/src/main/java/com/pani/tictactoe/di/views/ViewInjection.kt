package com.pani.tictactoe.di.views

import android.view.View
import dagger.internal.Preconditions

object ViewInjection {
    /**
     * Injects the view
     */
    fun inject(view: View) {
        Preconditions.checkNotNull(view, "view")
        val hasViewInjector =
            findHasViewInjector(view)
        val viewInjector = hasViewInjector.viewInjector()
        Preconditions.checkNotNull(
            viewInjector,
            "%s.viewInjector() returned null",
            viewInjector.javaClass.canonicalName
        )
        viewInjector.inject(view)
    }

    private fun findHasViewInjector(view: View): HasViewInjector {
        if (view.parent != null) {
            var parent = view.parent
            if (parent is HasViewInjector) {
                return parent
            }
            while (parent.parent.also { parent = it } != null) {
                if (parent is HasViewInjector) {
                    return parent as HasViewInjector
                }
            }
        }
        if (view.context is HasViewInjector) {
            return view.context as HasViewInjector
        } else if (view.context.applicationContext is HasViewInjector) {
            return view.context.applicationContext as HasViewInjector
        }
        throw IllegalArgumentException(
            String.format(
                "No injector was found for %s", view.javaClass.canonicalName
            )
        )
    }
}