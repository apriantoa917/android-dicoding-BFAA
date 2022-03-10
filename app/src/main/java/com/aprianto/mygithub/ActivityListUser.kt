package com.aprianto.mygithub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ActivityListUser : AppCompatActivity() {

    private lateinit var rvUser: RecyclerView
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)

        rvUser = findViewById(R.id.rv_user)
        rvUser.setHasFixedSize(true)

        list.addAll(listUsers)
        showRecyclerList()


    }

    private val listUsers: ArrayList<User>
        get() {
            val name = resources.getStringArray(R.array.name)
            val username = resources.getStringArray(R.array.username)
            val location = resources.getStringArray(R.array.location)
            val repository = resources.getStringArray(R.array.repository)
            val company = resources.getStringArray(R.array.company)
            val following = resources.getStringArray(R.array.following)
            val followers = resources.getStringArray(R.array.followers)
            val avatar = resources.obtainTypedArray(R.array.avatar)
            val dataUsers = ArrayList<User>()
            for (i in name.indices) {
                val users = User(
                    username[i],
                    name[i],
                    location[i],
                    company[i],
                    avatar.getResourceId(i, -1),
                    followers[i],
                    following[i],
                    repository[i]
                )
                dataUsers.add(users)
            }
            return dataUsers
        }

    private fun showRecyclerList() {
        rvUser.layoutManager = LinearLayoutManager(this)
        val userAdapter = UserAdapter(list)
        rvUser.adapter = userAdapter

        userAdapter.setOnUserClickCallback(object : UserAdapter.onUserClickCallbak{
            override fun onItemClick(data: User) {
                val moveDetailActivity = Intent(this@ActivityListUser, ActivityDetailUser::class.java)
                moveDetailActivity.putExtra(ActivityDetailUser.EXTRA_USER, data)
                startActivity(moveDetailActivity)
            }

        })
    }


}