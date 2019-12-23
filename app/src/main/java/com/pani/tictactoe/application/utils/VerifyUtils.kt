@file:JvmName("VerifyUtils")

package com.pani.tictactoe.application.utils

import android.util.Patterns
import java.math.RoundingMode
import java.text.DecimalFormat

fun isValidEmail(target: CharSequence?): Boolean {
    return target != null && Patterns.EMAIL_ADDRESS.matcher(target).matches()
}

