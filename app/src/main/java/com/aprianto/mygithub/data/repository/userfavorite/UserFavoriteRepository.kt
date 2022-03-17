package com.aprianto.mygithub.data.repository.userfavorite

import android.app.Application
import androidx.lifecycle.LiveData
import com.aprianto.mygithub.data.model.UserFavorite
import com.aprianto.mygithub.data.database.UserFavoriteDao
import com.aprianto.mygithub.data.database.UserFavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserFavoriteRepository(application: Application) {
    private val mUserFavoriteDao: UserFavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = UserFavoriteRoomDatabase.getDatabase(application)
        mUserFavoriteDao = db.userFavoriteDao()
    }

    fun getAllFavorites(): LiveData<List<UserFavorite>> = mUserFavoriteDao.getAllFavorites()

    fun setFavorite(favorite: UserFavorite) {
        executorService.execute { mUserFavoriteDao.setFavorite(favorite) }
    }

    fun unsetFavorite(username:String) {
        executorService.execute { mUserFavoriteDao.unsetFavorite(username) }
    }

    fun checkFavorite(username:String):LiveData<UserFavorite> = mUserFavoriteDao.checkFavorite(username)

    fun removeAllFavorites() {
        executorService.execute { mUserFavoriteDao.removeAllFavorites() }
    }
}