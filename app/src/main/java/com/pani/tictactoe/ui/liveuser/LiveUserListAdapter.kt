package com.pani.tictactoe.ui.liveuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pani.tictactoe.R
import com.pani.tictactoe.application.utils.NetworkState
import com.pani.tictactoe.model.User

private const val LAYOUT_TYPE_ITEM = 0
private const val LAYOUT_TYPE_ITEM_HEADER = 1

class LiveUserListAdapter(
    private val clickListener: LiveUserViewHolder.CustomerClickListner) : PagedListAdapter<User, RecyclerView.ViewHolder>(
    User.diffCallback) {

    private var networkState: NetworkState? = null




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveUserViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_user, parent, false)

        return LiveUserViewHolder(itemView, clickListener)
    }



    override fun getItemViewType(position: Int): Int {
        return when(position){
             0 -> LAYOUT_TYPE_ITEM_HEADER
            else -> LAYOUT_TYPE_ITEM
        }

    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    fun setNetworkState(newState: NetworkState) {
        this.networkState = newState
        this.notifyDataSetChanged()
    }





    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LiveUserViewHolder -> {
                val item = this.getItem(position)
                if (item != null) {
                    item?.let { holder.bind(it) }
                }
            }
//            is LiveUserViewHolder ->{
//                if (liveUserListResponse != null) {
//                    liveUserListResponse?.let { holder.bind(it) }
//                }
//            }
        }
    }


}
