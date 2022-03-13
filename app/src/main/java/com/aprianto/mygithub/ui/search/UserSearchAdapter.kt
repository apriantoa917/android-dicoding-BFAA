package com.aprianto.mygithub.ui.search

import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aprianto.mygithub.R
import com.aprianto.mygithub.UserSearchResult
import com.aprianto.mygithub.ui.detail.DetailUserActivity
import com.aprianto.mygithub.utils.Constanta
import com.bumptech.glide.Glide

class UserSearchAdapter(private val context: Context) :
    RecyclerView.Adapter<UserSearchAdapter.ListViewHolder>() {

    private var data = mutableListOf<UserSearchResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_follow, parent, false)
        return ListViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val userData = data[position]
        holder.apply {
            tvUsername.text = userData.login
            Glide.with(context)
                .load(userData.avatarUrl)
                .into(imgAvatar)
            itemView.setOnClickListener {
                val moveDetailActivity = Intent(
                    context,
                    DetailUserActivity::class.java
                )
                moveDetailActivity.putExtra(Constanta.EXTRA_USER, userData.login)
                context.startActivity(moveDetailActivity)
            }
        }
    }

    fun initData(users: List<UserSearchResult>) {
        clearData()
        data = users.toMutableList()
    }

    fun clearData(){
        data.clear()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvUsername: TextView = itemView.findViewById(R.id.tv_username)
        var imgAvatar: ImageView = itemView.findViewById(R.id.img_avatar)
    }

}