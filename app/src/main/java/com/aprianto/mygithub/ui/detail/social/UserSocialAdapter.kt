package com.aprianto.mygithub.ui.detail.social


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aprianto.mygithub.R
import com.aprianto.mygithub.data.model.UserSocialResult
import com.bumptech.glide.Glide

class UserSocialAdapter :
    RecyclerView.Adapter<UserSocialAdapter.ViewHolder>() {

    private var data = mutableListOf<UserSocialResult>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_follow, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val socialData = data[position]
        holder.apply {
            tvUsername.text = StringBuilder("@").append(socialData.login)
            Glide.with(itemView)
                .load(socialData.avatarUrl)
                .into(imgAvatar)
        }
    }

    fun initData(socialData: List<UserSocialResult>) {
        data.clear()
        data = socialData.toMutableList()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvUsername: TextView = itemView.findViewById(R.id.tv_username)
        var imgAvatar: ImageView = itemView.findViewById(R.id.img_avatar)
    }
}