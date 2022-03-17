package com.aprianto.mygithub.data.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aprianto.mygithub.data.model.UserDetail
import com.aprianto.mygithub.data.model.UserFavorite
import com.aprianto.mygithub.data.repository.remote.ApiConfig
import com.aprianto.mygithub.data.repository.userfavorite.UserFavoriteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailViewModel(application: Application) : ViewModel() {

    private val mUserFavoriteRepository = UserFavoriteRepository(application)
    val userData = MutableLiveData<UserDetail>()
    val userFavorite = MutableLiveData<UserFavorite>()
    val error = MutableLiveData<String>()
    val isFavorite = MutableLiveData<Boolean>()

    fun setFavorite(favorite: UserFavorite) {
        mUserFavoriteRepository.setFavorite(favorite)
    }

    fun unsetFavorite(username:String) {
        mUserFavoriteRepository.unsetFavorite(username)
    }

    // inisialisasi sementara data untuk favorite
    fun initFavorite(favorite: UserFavorite) {
        userFavorite.postValue(favorite)
    }

    // jalankan query untuk check apakah user di like
    fun checkFavorite(username: String): LiveData<UserFavorite> =
        mUserFavoriteRepository.checkFavorite(username)

    // load data detail user dari API
    fun setUserData(username: String) {
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<UserDetail> {
            override fun onResponse(
                call: Call<UserDetail>,
                response: Response<UserDetail>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        userData.postValue(data)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                    error.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                error.postValue(t.message)
            }
        })
    }

    companion object {
        private val TAG =  UserDetailViewModel::class.simpleName
    }
}