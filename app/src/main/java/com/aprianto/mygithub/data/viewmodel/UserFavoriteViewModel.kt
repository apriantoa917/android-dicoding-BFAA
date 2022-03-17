package com.aprianto.mygithub.data.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aprianto.mygithub.data.model.UserFavorite
import com.aprianto.mygithub.data.repository.userfavorite.UserFavoriteRepository

class UserFavoriteViewModel(application: Application) : ViewModel() {

    private val mUserFavoriteRepository: UserFavoriteRepository =
        UserFavoriteRepository(application)

    fun getAllFavorites(): LiveData<List<UserFavorite>> = mUserFavoriteRepository.getAllFavorites()

    fun unsetFavorite(username:String) {
        mUserFavoriteRepository.unsetFavorite(username)
    }

    fun removeAllFavorites() {
        mUserFavoriteRepository.removeAllFavorites()
    }
}