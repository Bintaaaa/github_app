package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.api

import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>
}