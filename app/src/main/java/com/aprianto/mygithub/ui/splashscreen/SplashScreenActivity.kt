package com.aprianto.mygithub.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.aprianto.mygithub.databinding.ActivitySplashScreenBinding
import com.aprianto.mygithub.ui.search.UserSearchActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Timer().schedule(2000) {
            startActivity(Intent(this@SplashScreenActivity, UserSearchActivity::class.java))
            finish()
        }
    }
}