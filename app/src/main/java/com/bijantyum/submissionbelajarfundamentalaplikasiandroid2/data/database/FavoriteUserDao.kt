package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavorite(favorite: FavoriteUser)

    @Query("DELETE FROM favorite_user WHERE favorite_user.id = :id")
    fun removeFavorite(id: Int)

    @Query("SELECT * FROM favorite_user ORDER BY id DESC")
    fun getAllFavorite() : LiveData<List<FavoriteUser>>

    @Query("SELECT count(*) FROM favorite_user WHERE favorite_user.id = :id")
    suspend fun checkUser(id: Int): Int
}