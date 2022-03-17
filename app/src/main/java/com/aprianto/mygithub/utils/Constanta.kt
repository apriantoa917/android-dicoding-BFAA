package com.aprianto.mygithub.utils

import androidx.annotation.StringRes
import com.aprianto.mygithub.R

object Constanta {
    @StringRes
    val TAB_TITLES = intArrayOf(
        R.string.text_followers,
        R.string.text_following
    )
    const val EXTRA_USER = "extra_user"
    const val EXTRA_USERNAME = "username"
    const val EXTRA_FOLLOW_MODE = "followers"
    const val EXTRA_FOLLOWERS = "followers"
    const val EXTRA_FOLLOWING = "following"
    const val GITHUB_REQUEST_HEADERS = "Authorization: token ${com.aprianto.mygithub.BuildConfig.GITHUB_TOKEN}"


}