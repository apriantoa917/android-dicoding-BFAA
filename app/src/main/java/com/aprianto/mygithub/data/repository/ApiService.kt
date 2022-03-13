package com.aprianto.mygithub.data.repository

import com.aprianto.mygithub.data.model.UserDetail
import com.aprianto.mygithub.UserSocialResult
import com.aprianto.mygithub.UserSearch
import com.aprianto.mygithub.utils.Constanta
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token ${Constanta.GITHUB_PAT}")
    fun getSearchResult(
        @Query("q") id: String
    ): Call<UserSearch>

    @GET("users/{login}")
    @Headers("Authorization: token ${Constanta.GITHUB_PAT}")
    fun getDetailUser(
        @Path("login") id: String
    ): Call<UserDetail>

    @GET("users/{login}/followers")
    @Headers("Authorization: token ${Constanta.GITHUB_PAT}")
    fun getUserFollowers(
        @Path("login") id: String
    ): Call<List<UserSocialResult>>

    @GET("users/{login}/following")
    @Headers("Authorization: token ${Constanta.GITHUB_PAT}")
    fun getUserFollowing(
        @Path("login") id: String
    ): Call<List<UserSocialResult>>
}