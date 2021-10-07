package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.following

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.api.ApiConfig
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {
    private var _listFollowing = MutableLiveData<ArrayList<User>>()
    fun getListFollowing(): LiveData<ArrayList<User>> = _listFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setListFollowing(usernanme: String) {
        _isLoading.value = true
        ApiConfig.getApiService()
            .getFollowing(usernanme)
            .enqueue(object : Callback<ArrayList<User>> {
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful) {
                        _isLoading.value = false
                        _listFollowing.postValue(response.body())
                    }
                    Log.d(TAG, "onResponse: ${response.message()}")
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    _isLoading.value = false
                    Log.d(TAG, "onResponse: ${t.message}")
                }

            })

    }


    companion object {
        private const val TAG = "FollowingViewModel"
    }
}