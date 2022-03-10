package com.aprianto.mygithub

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ActivityDetailUser : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val img_avatar: ImageView = findViewById(R.id.img_avatar)
        val tv_name: TextView = findViewById(R.id.tv_user_name)
        val tv_username: TextView = findViewById(R.id.tv_user_username)
        val tv_location: TextView = findViewById(R.id.tv_location)
        val tv_company: TextView = findViewById(R.id.tv_company)
        val tv_followers: TextView = findViewById(R.id.tv_followers)
        val tv_following: TextView = findViewById(R.id.tv_following)
        val tv_repository: TextView = findViewById(R.id.tv_repository)
        val btn_repo: ImageButton = findViewById(R.id.img_btn_repository)
        val btn_share: ImageButton = findViewById(R.id.img_btn_share)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        img_avatar.setImageResource(user.avatar)
        tv_name.text = user.name
        tv_username.text = "@${user.username}"
        tv_location.text = user.location
        tv_company.text = user.company
        tv_followers.text = user.followers
        tv_following.text = user.following
        tv_repository.text = user.repository

        btn_repo.setOnClickListener {
            val url = "https://github.com/${user.username}?tab=repositories"
            val openGithubRepo = Intent(Intent.ACTION_VIEW)
            openGithubRepo.data = Uri.parse(url)
            startActivity(openGithubRepo)
        }

        btn_share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    "Halo! Saya menggunakan github sebagai ${user.name}. Anda bisa cek sekarang di link berikut : https://github.com/${user.username}"
                )
                type = "text/plain"
            }

            val openShareProfile = Intent.createChooser(sendIntent, null)
            startActivity(openShareProfile)
        }
    }
}