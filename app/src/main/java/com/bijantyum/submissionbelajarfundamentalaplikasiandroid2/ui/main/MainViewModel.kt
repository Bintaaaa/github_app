package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.api.ApiConfig
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.model.User
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _listUsers = MutableLiveData<ArrayList<User>>()
    fun getSearchUsers(): LiveData<ArrayList<User>> = _listUsers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun setSearchUsers(query: String){
       ApiConfig.getApiService()
           .getSearchUsers(query)
           .enqueue(object : Callback<UserResponse>{
               override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                   if(response.isSuccessful){
                       _isLoading.value = false
                       _listUsers.postValue(response.body()?.items)
                   }
               }

               override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                   _isLoading.value = true
                   Log.d(TAG, "${t.message}")
               }

           })
    }


    companion object{
        private const val TAG = "MainViewModel"
    }
}