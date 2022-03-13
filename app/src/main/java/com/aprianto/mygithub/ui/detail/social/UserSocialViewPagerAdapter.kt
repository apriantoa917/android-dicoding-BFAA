package com.aprianto.mygithub.ui.detail.social

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aprianto.mygithub.utils.Constanta

class UserSocialViewPagerAdapter(activity: AppCompatActivity, private val username: String) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return FragmentFollow.newInstance(
            username,
            when (position) {
                0 -> Constanta.EXTRA_FOLLOWERS
                else -> Constanta.EXTRA_FOLLOWING
            }
        )
    }

    override fun getItemCount(): Int {
        return 2
    }
}