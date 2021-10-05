package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.api

import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.model.DetailUserResponse
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.model.User
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}