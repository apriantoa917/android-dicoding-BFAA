package com.aprianto.mygithub.data.repository.remote

import com.aprianto.mygithub.data.model.UserDetail
import com.aprianto.mygithub.data.model.UserSocialResult
import com.aprianto.mygithub.data.model.UserSearch
import com.aprianto.mygithub.utils.Constanta
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers(Constanta.GITHUB_REQUEST_HEADERS)
    fun getSearchResult(
        @Query("q") id: String
    ): Call<UserSearch>

    @GET("users/{login}")
    @Headers(Constanta.GITHUB_REQUEST_HEADERS)
    fun getDetailUser(
        @Path("login") id: String
    ): Call<UserDetail>

    @GET("users/{login}/followers")
    @Headers(Constanta.GITHUB_REQUEST_HEADERS)
    fun getUserFollowers(
        @Path("login") id: String
    ): Call<List<UserSocialResult>>

    @GET("users/{login}/following")
    @Headers(Constanta.GITHUB_REQUEST_HEADERS)
    fun getUserFollowing(
        @Path("login") id: String
    ): Call<List<UserSocialResult>>
}