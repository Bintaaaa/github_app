package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.menu.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.database.FavoriteUser
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.database.FavoriteUserRepository

class FavoriteViewModel(application: Application) : ViewModel() {
    private val mFavoriteUserRepository: FavoriteUserRepository = FavoriteUserRepository(application)
    fun getAllFavorite(): LiveData<List<FavoriteUser>> = mFavoriteUserRepository.getAllFavorite()
}