package com.aprianto.mygithub

import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val listUser: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    private lateinit var onUserClickCallback: onUserClickCallbak

    fun setOnUserClickCallback(onUserClickCallbak: onUserClickCallbak){
        this.onUserClickCallback = onUserClickCallbak
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (username, name, location, company, avatar, followers, following, repository) = listUser[position]
        holder.imgAvatar.setImageResource(avatar)
        holder.tvName.text = name
        holder.tvLocation.text = location
        holder.tvRepository.text = repository
        holder.itemView.setOnClickListener {
            onUserClickCallback.onItemClick(listUser[holder.adapterPosition])
        }
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_user_name)
        var tvRepository: TextView = itemView.findViewById(R.id.tv_repository)
        var tvLocation: TextView = itemView.findViewById(R.id.tv_location)
        var imgAvatar: ImageView = itemView.findViewById(R.id.img_avatar)
    }

    interface onUserClickCallbak {
        fun onItemClick(data: User)
    }

}