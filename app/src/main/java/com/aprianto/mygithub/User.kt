package com.aprianto.mygithub

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var username: String,
    var name: String,
    var location: String,
    var company:String,
    var avatar:Int,
    var followers: String,
    var following: String,
    var repository: String
) : Parcelable