package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.helper

import androidx.recyclerview.widget.DiffUtil
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.model.User

class UserDiffCallBack(private val mOldUser: List<User>, private val mNewUser: List<User>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldUser.size
    }

    override fun getNewListSize(): Int {
        return mNewUser.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldUser[oldItemPosition].id == mNewUser[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = mOldUser[oldItemPosition]
        val newUser = mNewUser[newItemPosition]
        return oldUser.id == newUser.id &&
                oldUser.login == newUser.login &&
                oldUser.avatarUrl == newUser.avatarUrl &&
                oldUser.url == newUser.url
    }

}