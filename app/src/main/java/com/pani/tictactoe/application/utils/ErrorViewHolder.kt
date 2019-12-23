package com.pani.tictactoe.application.utils

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.pani.tictactoe.R

class ErrorViewHolder(
        private val errorView: View,
        private val retryListener: RetryListener
) : RecyclerView.ViewHolder(errorView) {
//    private val retryButton: AppCompatButton = this.errorView.findViewById(R.id.retryButton)
//
//    init {
//        this.retryButton.setOnClickListener {
//            this.retryListener.retry()
//        }
//    }
}

interface RetryListener {
    fun retry()
}
