package com.aprianto.mygithub.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aprianto.mygithub.data.model.UserFavorite

@Dao
interface UserFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun setFavorite(favorite: UserFavorite)

    @Query("DELETE FROM UserFavorite WHERE username = :username")
    fun unsetFavorite(username: String)

    @Query("DELETE FROM UserFavorite")
    fun removeAllFavorites()

    @Query("SELECT * from UserFavorite ORDER BY created_at DESC")
    fun getAllFavorites(): LiveData<List<UserFavorite>>

    @Query("SELECT * FROM UserFavorite WHERE username = :username")
    fun checkFavorite(username: String): LiveData<UserFavorite>
}