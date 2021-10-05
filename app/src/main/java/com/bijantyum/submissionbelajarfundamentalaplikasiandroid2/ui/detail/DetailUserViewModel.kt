package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.api.ApiConfig
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.model.DetailUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel: ViewModel() {
    private val _user = MutableLiveData<DetailUserResponse>()
    val user: LiveData<DetailUserResponse> = _user

    fun setUserDetail(username: String){
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
                    Log.d(TAG, "${t.message}")
                }

            })
    }

    companion object{
        const val TAG = "DetailUserViewModel"
    }
}