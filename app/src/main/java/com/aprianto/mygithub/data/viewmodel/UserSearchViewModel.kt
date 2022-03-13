package com.aprianto.mygithub.data.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aprianto.mygithub.UserSearch
import com.aprianto.mygithub.UserSearchResult
import com.aprianto.mygithub.data.repository.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserSearchViewModel : ViewModel() {

    val resultData = MutableLiveData<List<UserSearchResult>>()
    val loading = MutableLiveData(View.GONE)
    val illustration = MutableLiveData(View.VISIBLE)
    val error = MutableLiveData<String>()

    fun getSearchResult(keyword: String) {
        loading.postValue(View.VISIBLE)
        val client = ApiConfig.getApiService().getSearchResult(keyword)
        client.enqueue(object : Callback<UserSearch> {
            override fun onResponse(
                call: Call<UserSearch>,
                response: Response<UserSearch>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    loading.postValue(View.GONE)
                    if (data?.totalCount == 0) {
                        // jika data tidak ditemukan -> set null, di activity akan diarahkan ke clear data (menghindari NPE)
                        resultData.postValue(null)
                        illustration.postValue(View.VISIBLE)
                        error.postValue("Pengguna $keyword tidak ditemukan")
                    } else {
                        resultData.postValue(data?.items)
                        illustration.postValue(View.GONE)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                    error.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<UserSearch>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                error.postValue(t.message)
            }

        })
    }

    companion object {
        private const val TAG = "UserSearchViewModel"
    }

}