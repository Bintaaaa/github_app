package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.helper

import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.database.FavoriteUser
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.model.User

object ConvertToList {
    fun convert(users: List<FavoriteUser>): ArrayList<User> {
        val listUser = ArrayList<User>()
        for (user in users){
            val userMapped = User(
                user.username,user.id,user.url,user.avatar
            )
            listUser.add(userMapped)
        }
        return listUser
    }
}