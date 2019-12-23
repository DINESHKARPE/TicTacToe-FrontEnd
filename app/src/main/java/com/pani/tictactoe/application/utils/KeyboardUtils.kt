@file:JvmName("KeyboardUtils")

package deliverr.deliverrconsumer.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

// TODO: we should determine the best way to do this
fun hideKeyboard(activity: Activity?) {
    if (activity != null) {
        val currentFocus = activity.currentFocus

        if (currentFocus != null) {
            hideKeyboard(activity, currentFocus)
        } else {
            // TODO: managing the keyboard is absolutely the worst..
        }
    }
}

fun hideKeyboard(context: Context, currentFocus: View) {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
}

fun showKeyboard(activity: Activity?) {
    if (activity != null) {
        val currentFocus = activity.currentFocus

        if (currentFocus != null) {
            showKeyboard(activity, currentFocus)
        } else {
            // TODO: managing the keyboard is absolutely the worst..
        }
    }
}

fun showKeyboard(context: Context?, currentFocus: View?) {
    if (context != null && currentFocus != null) {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        inputMethodManager.showSoftInput(currentFocus, InputMethodManager.SHOW_IMPLICIT)
    }
}
