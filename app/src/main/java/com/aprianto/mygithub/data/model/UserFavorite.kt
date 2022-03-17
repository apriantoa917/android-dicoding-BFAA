package com.aprianto.mygithub.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
class UserFavorite (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "avatar_URL")
    var avatar_URL: String? = null,

    @ColumnInfo(name = "created_at")
    var created_at: String? = null,
) : Parcelable