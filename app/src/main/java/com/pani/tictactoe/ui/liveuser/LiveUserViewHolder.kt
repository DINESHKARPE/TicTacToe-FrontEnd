package com.pani.tictactoe.ui.liveuser

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.pani.tictactoe.R
import com.pani.tictactoe.model.User

class LiveUserViewHolder(
    rootView: View,
    private val clickListener: CustomerClickListner
) : RecyclerView.ViewHolder(rootView) {
    private val name: AppCompatTextView = rootView.findViewById(R.id.username)

    fun bind(item: User) {
        name.text = item.name + item.room


        itemView.findViewById<AppCompatImageView>(R.id.play_button).setOnClickListener {
            clickListener.onClickEngineer(item)
        }
    }
    interface CustomerClickListner {
        fun onClickEngineer(customer: User)
    }



}
