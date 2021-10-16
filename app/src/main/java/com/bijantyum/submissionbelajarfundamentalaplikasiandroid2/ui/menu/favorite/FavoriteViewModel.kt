package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.menu.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.database.FavoriteUser
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.database.FavoriteUserRepository

class FavoriteViewModel(application: Application) : AndroidViewModel(application){
    private val mFavoriteUserRepository: FavoriteUserRepository = FavoriteUserRepository(application)
    fun getAllFavorite(): LiveData<List<FavoriteUser>> = mFavoriteUserRepository.getAllFavorite()
}