package com.aprianto.mygithub.data.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aprianto.mygithub.UserSocialResult
import com.aprianto.mygithub.data.model.UserDetail
import com.aprianto.mygithub.data.repository.ApiConfig
import com.aprianto.mygithub.utils.Constanta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailViewModel : ViewModel() {

    val socialData = MutableLiveData<List<UserSocialResult>>()
    val userData = MutableLiveData<UserDetail>()
    val loading = MutableLiveData(View.VISIBLE)
    val error = MutableLiveData<String>()

    fun setSocialData(followMode: String, username: String) {
        val client = when (followMode) {
            Constanta.EXTRA_FOLLOWERS -> ApiConfig.getApiService().getUserFollowers(username)
            else -> ApiConfig.getApiService().getUserFollowing(username)
        }
        client.enqueue(object : Callback<List<UserSocialResult>> {
            override fun onResponse(
                call: Call<List<UserSocialResult>>,
                response: Response<List<UserSocialResult>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    socialData.postValue(data)
                    loading.postValue(View.GONE)
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                    error.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<List<UserSocialResult>>, t: Throwable) {
                Log.e(TAG, "onFailure: $t")
                error.postValue(t.message)
            }
        })
    }

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
        private const val TAG = "UserDetailViewModel"
    }

}