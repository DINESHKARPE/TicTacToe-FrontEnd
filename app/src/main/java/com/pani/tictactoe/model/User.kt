package com.pani.tictactoe.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@SuppressLint("ParcelCreator")
class User : Parcelable ,Serializable{
    @SerializedName("username")
    var name: String? = null

    @SerializedName("room")
    var room: String? = null

    protected constructor(`in`: Parcel) {
        name = `in`.readString()
        room = `in`.readString()
    }

    constructor() {}

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
    }

    companion object {
        val cREATOR: Parcelable.Creator<User?> =

            object : Parcelable.Creator<User?> {
                override fun createFromParcel(`in`: Parcel): User? {
                    return User(`in`)
                }

                override fun newArray(size: Int): Array<User?> {
                    return arrayOfNulls(size)
                }
            }

        val diffCallback: DiffUtil.ItemCallback<User> =
            object : DiffUtil.ItemCallback<User>() {
                override fun areItemsTheSame(
                    oldItem: User,
                    newItem: User
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: User,
                    newItem: User
                ): Boolean {
                    return oldItem.name == newItem.name
                }
            }
    }


}