package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.followers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.api.ApiConfig
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {
    private var _listFollowers = MutableLiveData<ArrayList<User>>()
    fun getListFollowers(): LiveData<ArrayList<User>> = _listFollowers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setListFollowers(usernanme: String){
        _isLoading.value = true
        ApiConfig.getApiService()
            .getFollowers(usernanme)
            .enqueue(object : Callback<ArrayList<User>>{
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                if(response.isSuccessful){
                    _isLoading.value = false
                    _listFollowers.postValue(response.body())
                }
                Log.d(TAG,"onResponse: ${response.message()}")
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG,"onResponse: ${t.message}")
            }

        })

    }


    companion object {
        private const val TAG = "FollowersViewModel"
    }
}