package com.aprianto.mygithub.utils.callback

import androidx.recyclerview.widget.DiffUtil
import com.aprianto.mygithub.data.model.UserFavorite

class UserFavoriteDiffCallback(
    private val mOldFavoriteList: List<UserFavorite>,
    private val mNewFavoriteList: List<UserFavorite>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return mOldFavoriteList.size
    }

    override fun getNewListSize(): Int {
        return mNewFavoriteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldFavoriteList[oldItemPosition].username == mNewFavoriteList[newItemPosition].username
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFavorite = mOldFavoriteList[oldItemPosition]
        val newFavorite = mNewFavoriteList[newItemPosition]
        return oldFavorite.username == newFavorite.username
    }
}