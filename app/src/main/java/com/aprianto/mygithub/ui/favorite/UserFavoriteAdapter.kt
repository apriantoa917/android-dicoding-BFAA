package com.aprianto.mygithub.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aprianto.mygithub.data.model.UserFavorite
import com.aprianto.mygithub.databinding.ItemUserFavoriteBinding
import com.aprianto.mygithub.ui.detail.DetailUserActivity
import com.aprianto.mygithub.utils.Constanta
import com.aprianto.mygithub.utils.Helper
import com.aprianto.mygithub.utils.callback.UserFavoriteDiffCallback
import com.bumptech.glide.Glide

class UserFavoriteAdapter : RecyclerView.Adapter<UserFavoriteAdapter.FavoriteViewHolder>() {

    private val listFavorites = ArrayList<UserFavorite>()

    fun setListFavorites(listFavorites: List<UserFavorite>) {
        val diffCallback = UserFavoriteDiffCallback(this.listFavorites, listFavorites)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listFavorites.clear()
        this.listFavorites.addAll(listFavorites)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getSwipedUsername(position: Int): String {
        return listFavorites[position].username
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding =
            ItemUserFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFavorites[position])
    }

    override fun getItemCount(): Int {
        return listFavorites.size
    }

    inner class FavoriteViewHolder(private val binding: ItemUserFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: UserFavorite) {
            with(binding) {
                tvUsername.text = StringBuilder("@").append(favorite.username)
                tvName.text = favorite.name ?: favorite.username
                tvTime.text = StringBuilder("Disimpan pada ").append(favorite.created_at?.let {
                    Helper.getSimpleDateFormat(
                        it
                    )
                })
                Glide.with(itemView)
                    .load(favorite.avatar_URL)
                    .into(imgAvatar)
                itemView.setOnClickListener {
                    val moveDetailActivity = Intent(
                        it.context,
                        DetailUserActivity::class.java
                    )
                    moveDetailActivity.putExtra(Constanta.EXTRA_USER, favorite.username)
                    it.context.startActivity(moveDetailActivity)
                }
            }
        }
    }

}



