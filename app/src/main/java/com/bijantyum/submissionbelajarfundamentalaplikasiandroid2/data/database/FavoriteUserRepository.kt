package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.database

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteUserRepository(application: Application) {
    private val mFavoriteUserDao: FavoriteUserDao

    init {
        val db = FavoriteUserDatabase.getDatabase(application)
        mFavoriteUserDao = db.favoriteuserDao()
    }

    fun getAllFavorite():LiveData<List<FavoriteUser>> = mFavoriteUserDao.getAllFavorite()

    fun addToFavorite(id: Int?, username: String?, avatar: String?, url: String?){
        CoroutineScope(Dispatchers.IO).launch {
            val user = FavoriteUser(
                id,
                username,
                avatar,
                url
            )
            mFavoriteUserDao.addToFavorite(user)
        }
    }
    suspend fun checkUser(id: Int) = mFavoriteUserDao.checkUser(id)
    fun removeFavorite(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            mFavoriteUserDao.removeFavorite(id)
        }
    }
}