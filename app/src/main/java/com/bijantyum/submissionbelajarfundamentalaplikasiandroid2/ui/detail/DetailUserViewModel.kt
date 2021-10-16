package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.api.ApiConfig
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.database.FavoriteUserRepository
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.model.DetailUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application): AndroidViewModel(application) {
    private val _user = MutableLiveData<DetailUserResponse>()
    fun getUserDetail(): LiveData<DetailUserResponse> = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val mFavoriteUserRepository: FavoriteUserRepository = FavoriteUserRepository(application)

    fun setUserDetail(username: String?){
        _isLoading.value = true
        if (username != null) {
            _isLoading.value = false
            ApiConfig.getApiService()
                .getDetailUser(username)
                .enqueue(object : Callback<DetailUserResponse>{
                    override fun onResponse(
                        call: Call<DetailUserResponse>,
                        response: Response<DetailUserResponse>
                    ) {
                        if (response.isSuccessful){
                            _user.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                        _isLoading.value = false
                        Log.d(TAG, "${t.message}")
                    }

                })
        }
    }

    fun addToFavorite(id: Int?, username: String?, avatar: String?, url: String?) = mFavoriteUserRepository.addToFavorite(id,username,avatar,url)
    fun removeFromFavorite(id: Int) = mFavoriteUserRepository.removeFavorite(id)

    suspend fun checkUser(id: Int) = mFavoriteUserRepository.checkUser(id)
    companion object{
        const val TAG = "DetailUserViewModel"
    }
}